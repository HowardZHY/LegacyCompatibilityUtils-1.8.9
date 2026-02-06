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

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.objectweb.asm.commons.Remapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public abstract class BaseRemapper extends Remapper {

    protected ImmutableBiMap<String, String> classNameBiMap = ImmutableBiMap.of();
    protected ImmutableBiMap<String, String> reverseClassMap = ImmutableBiMap.of();

    protected Map<String, Map<String, String>> rawFieldMaps = Maps.newHashMap();
    protected Map<String, Map<String, String>> rawMethodMaps = Maps.newHashMap();

    public Map<String, Map<String, String>> fieldNameMaps = Maps.newHashMap();
    public Map<String, Map<String, String>> methodNameMaps = Maps.newHashMap();

    protected Map<String, Map<String, String>> fieldDescriptions = Maps.newHashMap();

    protected Set<String> negativeCacheMethods = Sets.newHashSet();
    protected Set<String> negativeCacheFields = Sets.newHashSet();

    protected static final class ParentInfo {
        private final String superName;
        private final String[] interfaces;

        private ParentInfo(String superName, String[] interfaces) {
            this.superName = superName;
            this.interfaces = interfaces;
        }
    }

    protected abstract ParentInfo getParentInfo(String name);

    protected void initializeCaches() {
        methodNameMaps = Maps.newHashMapWithExpectedSize(rawMethodMaps.size());
        fieldNameMaps = Maps.newHashMapWithExpectedSize(rawFieldMaps.size());
    }

    protected void finalizeMappings(ImmutableBiMap<String, String> classMap) {
        classNameBiMap = classMap;
        reverseClassMap = classMap.inverse();
        initializeCaches();
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
        try {
            if (fieldMap.get(name + ":null") != null) {
                return fieldMap.get(name + ":null");
            }
        } catch (NullPointerException ignored) {
            return name;
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
            findAndMergeSuperMaps(className);
            if (!fieldNameMaps.containsKey(className)) {
                negativeCacheFields.add(className);
            }
        }
        return fieldNameMaps.get(className);
    }

    public Map<String, String> getMethodMap(String className) {
        if (!methodNameMaps.containsKey(className) && !negativeCacheMethods.contains(className)) {
            findAndMergeSuperMaps(className);
            if (!methodNameMaps.containsKey(className)) {
                negativeCacheMethods.add(className);
            }
        }
        return methodNameMaps.get(className);
    }

    protected void findAndMergeSuperMaps(String name) {
        ParentInfo info = getParentInfo(name);
        if (info == null) {
            return;
        }
        mergeSuperMaps(name, info.superName, info.interfaces);
    }

    protected void mergeSuperMaps(String name, String superName, String[] interfaces) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return;
        }
        if (Strings.isNullOrEmpty(superName)) {
            return;
        }
        List<String> allParents = ImmutableList.<String>builder().add(superName).addAll(Arrays.asList(interfaces)).build();
        for (String parentThing : allParents) {
            if (!methodNameMaps.containsKey(parentThing) && !negativeCacheMethods.contains(parentThing)) {
                findAndMergeSuperMaps(parentThing);
            }
            if (!fieldNameMaps.containsKey(parentThing) && !negativeCacheFields.contains(parentThing)) {
                findAndMergeSuperMaps(parentThing);
            }
        }
        Map<String, String> methodMap = Maps.newHashMap();
        Map<String, String> fieldMap = Maps.newHashMap();
        for (String parentThing : allParents) {
            if (methodNameMaps.containsKey(parentThing)) {
                methodMap.putAll(methodNameMaps.get(parentThing));
            }
            if (fieldNameMaps.containsKey(parentThing)) {
                fieldMap.putAll(fieldNameMaps.get(parentThing));
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

    public Set<String> getObfedClasses() {
        return ImmutableSet.copyOf(classNameBiMap.keySet());
    }
}
