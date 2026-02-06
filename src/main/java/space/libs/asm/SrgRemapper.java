/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package space.libs.asm;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.*;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.io.Resources.getResource;

@SuppressWarnings("all")
public abstract class SrgRemapper extends Remapper {

    protected BiMap<String, String> classNameBiMap = ImmutableBiMap.of();
    protected BiMap<String, String> reverseClassMap = ImmutableBiMap.of();
    protected Map<String, Map<String, String>> rawFieldMaps = Maps.newHashMap();
    protected Map<String, Map<String, String>> rawMethodMaps = Maps.newHashMap();
    protected Map<String, Map<String, String>> fieldNameMaps = Maps.newHashMap();
    protected Map<String, Map<String, String>> methodNameMaps = Maps.newHashMap();
    protected Map<String, Map<String, String>> fieldDescriptions = Maps.newHashMap();

    private final Set<String> negativeCacheMethods = Sets.newHashSet();
    private final Set<String> negativeCacheFields = Sets.newHashSet();

    protected void loadMappings(String deobfFileName) throws IOException {
        URL mappings = getResource(deobfFileName);
        CharSource srgSource = Resources.asCharSource(mappings, Charsets.UTF_8);
        List<String> srgList = srgSource.readLines();
        rawMethodMaps = Maps.newHashMap();
        rawFieldMaps = Maps.newHashMap();
        Builder<String, String> builder = ImmutableBiMap.<String, String>builder();
        Splitter splitter = Splitter.on(CharMatcher.anyOf(": ")).omitEmptyStrings().trimResults();
        for (String line : srgList) {
            String[] parts = Iterables.toArray(splitter.split(line), String.class);
            if (parts.length == 0) {
                continue;
            }
            String typ = parts[0];
            if ("CL".equals(typ)) {
                parseClass(builder, parts);
            } else if ("MD".equals(typ)) {
                parseMethod(parts);
            } else if ("FD".equals(typ)) {
                parseField(parts);
            }
        }
        classNameBiMap = builder.build();
        reverseClassMap = classNameBiMap.inverse();
        methodNameMaps = Maps.newHashMapWithExpectedSize(rawMethodMaps.size());
        fieldNameMaps = Maps.newHashMapWithExpectedSize(rawFieldMaps.size());
    }

    protected void parseClass(Builder<String, String> builder, String[] parts) {
        if (parts.length >= 3) {
            builder.put(parts[1], parts[2]);
        }
    }

    protected void parseField(String[] parts) {
        if (parts.length < 3) {
            return;
        }
        String oldSrg = parts[1];
        int lastOld = oldSrg.lastIndexOf('/');
        String cl = oldSrg.substring(0, lastOld);
        String oldName = oldSrg.substring(lastOld + 1);
        String newSrg = parts[2];
        int lastNew = newSrg.lastIndexOf('/');
        String newName = newSrg.substring(lastNew + 1);
        if (!rawFieldMaps.containsKey(cl)) {
            rawFieldMaps.put(cl, Maps.<String, String>newHashMap());
        }
        String fieldType = getFieldType(cl, oldName);
        if (fieldType != null) {
            rawFieldMaps.get(cl).put(oldName + ":" + fieldType, newName);
        }
        if (shouldAddNullFieldMapping(fieldType)) {
            rawFieldMaps.get(cl).put(oldName + ":null", newName);
        }
    }

    protected boolean shouldAddNullFieldMapping(String fieldType) {
        return true;
    }

    protected void parseMethod(String[] parts) {
        if (parts.length < 4) {
            return;
        }
        String oldSrg = parts[1];
        int lastOld = oldSrg.lastIndexOf('/');
        String cl = oldSrg.substring(0, lastOld);
        String oldName = oldSrg.substring(lastOld + 1);
        String sig = parts[2];
        String newSrg = parts[3];
        int lastNew = newSrg.lastIndexOf('/');
        String newName = newSrg.substring(lastNew + 1);
        if (!rawMethodMaps.containsKey(cl)) {
            rawMethodMaps.put(cl, Maps.<String, String>newHashMap());
        }
        rawMethodMaps.get(cl).put(oldName + sig, newName);
    }

    protected byte[] getClassBytes(String name) {
        try {
            return Launch.classLoader.getClassBytes(name);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    protected String getFieldType(String owner, String name) {
        Map<String, String> fieldMap = fieldDescriptions.get(owner);
        if (fieldMap != null) {
            return fieldMap.get(name);
        }
        byte[] classBytes = getClassBytes(owner);
        if (classBytes == null) {
            return null;
        }
        ClassReader cr = new ClassReader(classBytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        Map<String, String> result = Maps.newHashMapWithExpectedSize(classNode.fields.size());
        String match = null;
        for (FieldNode fieldNode : (List<FieldNode>) classNode.fields) {
            result.put(fieldNode.name, fieldNode.desc);
            if (fieldNode.name.equals(name)) {
                match = fieldNode.desc;
            }
        }
        fieldDescriptions.put(owner, result);
        return match;
    }

    @Override
    public String map(String typeName) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return typeName;
        }
        if (classNameBiMap.containsKey(typeName)) {
            return classNameBiMap.get(typeName);
        }
        int dollarIdx = typeName.lastIndexOf('$');
        if (dollarIdx > -1) {
            return map(typeName.substring(0, dollarIdx)) + "$" + typeName.substring(dollarIdx + 1);
        }
        return typeName;
    }

    public String unmap(String typeName) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return typeName;
        }
        if (classNameBiMap.containsValue(typeName)) {
            return classNameBiMap.inverse().get(typeName);
        }
        int dollarIdx = typeName.lastIndexOf('$');
        if (dollarIdx > -1) {
            return unmap(typeName.substring(0, dollarIdx)) + "$" + typeName.substring(dollarIdx + 1);
        }
        return typeName;
    }

    @Override
    public String mapFieldName(String owner, String name, String desc) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return name;
        }
        Map<String, String> fieldMap = getFieldMap(owner);
        if (fieldMap != null && fieldMap.containsKey(name + ":" + desc)) {
            return fieldMap.get(name + ":" + desc);
        }
        if (fieldMap != null && fieldMap.containsKey(name + ":null")) {
            return fieldMap.get(name + ":null");
        }
        return name;
    }

    @Override
    public String mapMethodName(String owner, String name, String desc) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return name;
        }
        Map<String, String> methodMap = getMethodMap(owner);
        String methodDescriptor = name + desc;
        return methodMap != null && methodMap.containsKey(methodDescriptor) ? methodMap.get(methodDescriptor) : name;
    }

    public Map<String, String> getFieldMap(String className) {
        if (!fieldNameMaps.containsKey(className) && !negativeCacheFields.contains(className)) {
            loadSuperMaps(className);
            if (!fieldNameMaps.containsKey(className)) {
                negativeCacheFields.add(className);
            }
        }
        return fieldNameMaps.get(className);
    }

    public Map<String, String> getMethodMap(String className) {
        if (!methodNameMaps.containsKey(className) && !negativeCacheMethods.contains(className)) {
            loadSuperMaps(className);
            if (!methodNameMaps.containsKey(className)) {
                negativeCacheMethods.add(className);
            }
        }
        return methodNameMaps.get(className);
    }

    protected void loadSuperMaps(String name) {
        SuperInfo info = getSuperInfo(name);
        if (info == null) {
            return;
        }
        mergeSuperMaps(name, info.superName, info.interfaces);
    }

    protected SuperInfo getSuperInfo(String name) {
        byte[] classBytes = getClassBytes(name);
        if (classBytes == null) {
            return null;
        }
        ClassReader cr = new ClassReader(classBytes);
        return new SuperInfo(cr.getSuperName(), cr.getInterfaces());
    }

    public void mergeSuperMaps(String name, String superName, String[] interfaces) {
        if (Strings.isNullOrEmpty(superName)) {
            return;
        }
        String[] parents = new String[interfaces.length + 1];
        parents[0] = superName;
        System.arraycopy(interfaces, 0, parents, 1, interfaces.length);
        for (String parent : parents) {
            if (!fieldNameMaps.containsKey(parent) || !methodNameMaps.containsKey(parent)) {
                loadSuperMaps(parent);
            }
        }
        Map<String, String> methodMap = Maps.newHashMap();
        Map<String, String> fieldMap = Maps.newHashMap();
        for (String parent : parents) {
            if (methodNameMaps.containsKey(parent)) {
                methodMap.putAll(methodNameMaps.get(parent));
            }
            if (fieldNameMaps.containsKey(parent)) {
                fieldMap.putAll(fieldNameMaps.get(parent));
            }
        }
        if (rawMethodMaps.containsKey(name)) {
            methodMap.putAll(rawMethodMaps.get(name));
        }
        if (rawFieldMaps.containsKey(name)) {
            fieldMap.putAll(rawFieldMaps.get(name));
        }
        methodNameMaps.put(name, ImmutableMap.copyOf(methodMap));
        fieldNameMaps.put(name, ImmutableMap.copyOf(fieldMap));
    }

    protected static class SuperInfo {
        private final String superName;
        private final String[] interfaces;

        private SuperInfo(String superName, String[] interfaces) {
            this.superName = superName;
            this.interfaces = interfaces == null ? new String[0] : interfaces;
        }
    }
}
