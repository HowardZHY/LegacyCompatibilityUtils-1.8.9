/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */

package space.libs.asm.remap;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.common.patcher.ClassPatchManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import space.libs.core.CompatLibDebug;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.google.common.io.Resources.getResource;

@SuppressWarnings("all")
public class CustomRemapRemapper extends Remapper {

    public BiMap<String, String> classNameBiMap;

    public BiMap<String, String> reverseClassMap;

    private Map<String,Map<String,String>> rawFieldMaps;

    private Map<String,Map<String,String>> rawMethodMaps;

    public Map<String,Map<String,String>> fieldNameMaps;

    public Map<String,Map<String,String>> methodNameMaps;

    private LaunchClassLoader classLoader;

    public static final Logger LOGGER = LogManager.getLogger();

    public static boolean DEBUG_REMAPPING = CompatLibDebug.DEBUG_REMAP;

    public CustomRemapRemapper() {
        classNameBiMap = ImmutableBiMap.of();
        reverseClassMap = ImmutableBiMap.of();
        //this.setup((LaunchClassLoader) this.getClass().getClassLoader(), DEFAULT_MAPPINGS);
    }

    public CustomRemapRemapper(String name) {
        classNameBiMap = ImmutableBiMap.of();
        reverseClassMap = ImmutableBiMap.of();
        this.setup((LaunchClassLoader) this.getClass().getClassLoader(), name);
    }

    public void setup(LaunchClassLoader classLoader, String deobfFileName) {
        this.classLoader = classLoader;
        try {
            URL mappings = getResource(deobfFileName);
            CharSource srgSource = Resources.asCharSource(mappings, Charsets.UTF_8);
            List<String> srgList = srgSource.readLines();
            rawMethodMaps = Maps.newHashMap();
            rawFieldMaps = Maps.newHashMap();
            Builder<String, String> builder = ImmutableBiMap.<String,String>builder();
            Splitter splitter = Splitter.on(CharMatcher.anyOf(": ")).omitEmptyStrings().trimResults();
            for (String line : srgList) {
                String[] parts = Iterables.toArray(splitter.split(line),String.class);
                String typ = parts[0];
                if ("CL".equals(typ)) {
                    parseClass(builder, parts);
                }
                else if ("MD".equals(typ)) {
                    parseMethod(parts);
                }
                else if ("FD".equals(typ)) {
                    parseField(parts);
                }
            }
            classNameBiMap = builder.build();
            reverseClassMap = classNameBiMap.inverse();
        } catch (Exception e) {
            LOGGER.error("An error occurred loading the custom map data" + e);
        }
        methodNameMaps = Maps.newHashMapWithExpectedSize(rawMethodMaps.size());
        fieldNameMaps = Maps.newHashMapWithExpectedSize(rawFieldMaps.size());
    }

    public boolean isRemappedClass(String className) {
        return !map(className).equals(className);
    }

    private void parseField(String[] parts) {
        String oldSrg = parts[1];
        int lastOld = oldSrg.lastIndexOf('/');
        String cl = oldSrg.substring(0,lastOld);
        String oldName = oldSrg.substring(lastOld+1);
        String newSrg = parts[2];
        int lastNew = newSrg.lastIndexOf('/');
        String newName = newSrg.substring(lastNew+1);
        if (!rawFieldMaps.containsKey(cl)) {
            rawFieldMaps.put(cl, Maps.<String,String>newHashMap());
        }
        rawFieldMaps.get(cl).put(oldName + ":" + getFieldType(cl, oldName), newName);
        rawFieldMaps.get(cl).put(oldName + ":null", newName);
    }

    private Map<String,Map<String,String>> fieldDescriptions = Maps.newHashMap();

    private Set<String> negativeCacheMethods = Sets.newHashSet();

    private Set<String> negativeCacheFields = Sets.newHashSet();

    public String getRealName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return name;
        }
        if (name.contains("/")) {
            return name; // Not mapped by Forge
        }
        String mappedName = this.map(name);
        String realName = FMLDeobfuscatingRemapper.INSTANCE.unmap(mappedName);
        if (DEBUG_REMAPPING && (name != realName)) {
            LOGGER.info("Get " + name + "'s unmapped name " + realName + " from " + mappedName);
        }
        return realName;
    }

    public String getLegacyName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return name;
        }
        if (name.contains("/")) {
            return name; // Not mapped
        }
        String mappedName = FMLDeobfuscatingRemapper.INSTANCE.map(name);
        String legacyName = this.unmap(mappedName);
        return legacyName;
    }

    private static byte[] getBytes(String name) {
        try {
            return Launch.classLoader.getClassBytes(name);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    private String getFieldType(String owner, String name) {
        if (fieldDescriptions.containsKey(owner)) {
            return fieldDescriptions.get(owner).get(name);
        }
        synchronized (fieldDescriptions) {
            try {
                byte[] classBytes = ClassPatchManager.INSTANCE.getPatchedResource(getRealName(owner), map(owner).replace('/', '.'), classLoader);
                if (classBytes == null) {
                    return null;
                }
                ClassReader cr = new ClassReader(classBytes);
                ClassNode classNode = new ClassNode();
                cr.accept(classNode, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                Map<String,String> resMap = Maps.newHashMap();
                for (FieldNode fieldNode : (List<FieldNode>) classNode.fields) {
                    resMap.put(fieldNode.name, fieldNode.desc);
                }
                fieldDescriptions.put(owner, resMap);
                return resMap.get(name);
            } catch (Exception e) {
                LOGGER.error(e + " A critical exception occured reading a class file " + owner);
            }
            return null;
        }
    }

    private void parseClass(Builder<String, String> builder, String[] parts) {
        builder.put(parts[1],parts[2]);
    }

    private void parseMethod(String[] parts) {
        String oldSrg = parts[1];
        int lastOld = oldSrg.lastIndexOf('/');
        String cl = oldSrg.substring(0,lastOld);
        String oldName = oldSrg.substring(lastOld+1);
        String sig = parts[2];
        String newSrg = parts[3];
        int lastNew = newSrg.lastIndexOf('/');
        String newName = newSrg.substring(lastNew+1);
        if (!rawMethodMaps.containsKey(cl)) {
            rawMethodMaps.put(cl, Maps.<String,String>newHashMap());
        }
        rawMethodMaps.get(cl).put(oldName+sig, newName);
    }

    @Override
    public String mapFieldName(String owner, String name, String desc) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return name;
        }
        Map<String, String> fieldMap = getFieldMap(owner);
        if (fieldMap != null && fieldMap.containsKey(name + ":" + desc)) {
            return fieldMap.get(name + ":" + desc);
        } else {
            try {
                if (fieldMap.get(name + ":null") != null) {
                    if (DEBUG_REMAPPING && (!owner.contains("/") || owner.contains("net"))) {
                        LOGGER.info("Try map field without desc " + owner + "." + name + " to " + fieldMap.get(name + ":null"));
                    }
                    return fieldMap.get(name + ":null");
                }
            } catch (NullPointerException ignored) {
                if (DEBUG_REMAPPING) {
                    LOGGER.error("NPE when mapping field name: " + owner + "." + name + ":" + desc);
                }
            }
            return name;
        }
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
    public String mapMethodName(String owner, String name, String desc) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return name;
        }
        Map<String, String> methodMap = getMethodMap(owner);
        String methodDescriptor = name+desc;
        return methodMap != null && methodMap.containsKey(methodDescriptor) ? methodMap.get(methodDescriptor) : name;
    }

    public Map<String,String> getFieldMap(String className) {
        if (!fieldNameMaps.containsKey(className) && !negativeCacheFields.contains(className)) {
            findAndMergeSuperMaps(className);
            if (!fieldNameMaps.containsKey(className)) {
                negativeCacheFields.add(className);
            }
            if (DEBUG_REMAPPING && !className.startsWith("java")) {
                LOGGER.info("Field map for " + className + " : " + fieldNameMaps.get(className));
            }
        }
        return fieldNameMaps.get(className);
    }

    public Map<String,String> getMethodMap(String className) {
        if (!methodNameMaps.containsKey(className) && !negativeCacheMethods.contains(className)) {
            findAndMergeSuperMaps(className);
            if (!methodNameMaps.containsKey(className)) {
                negativeCacheMethods.add(className);
            }
            if (DEBUG_REMAPPING && !className.startsWith("java")) {
                // LOGGER.info("Method map for " + className + " : " + methodNameMaps.get(className));
            }
        }
        return methodNameMaps.get(className);
    }

    private void findAndMergeSuperMaps(String name) {
        try {
            String superName = null;
            String[] interfaces = new String[0];
            byte[] classBytes = ClassPatchManager.INSTANCE.getPatchedResource(getRealName(name), map(name), classLoader);
            if (classBytes != null) {
                ClassReader cr = new ClassReader(classBytes);
                superName = cr.getSuperName();
                interfaces = cr.getInterfaces();
            }
            String[] legacyInterfaces = new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                legacyInterfaces[i] = getLegacyName(interfaces[i]);
            }
            if (DEBUG_REMAPPING && (!Strings.isNullOrEmpty(name)) && (!Strings.isNullOrEmpty(superName)) && !name.startsWith("java")) {
                LOGGER.info("Try finding super map for " + name + " to " + superName);
            }
            mergeSuperMaps(name, getLegacyName(superName), legacyInterfaces);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mergeSuperMaps(String name, String superName, String[] interfaces) {
        if (classNameBiMap == null || classNameBiMap.isEmpty()) {
            return;
        }
        if (Strings.isNullOrEmpty(superName)) {
            return;
        }
        if (DEBUG_REMAPPING && (!name.startsWith("java") || !superName.startsWith("java"))) {
            LOGGER.info("Computing super maps for " + name + " & " + superName);
            for (String itf : interfaces) {
                LOGGER.info("Interfaces: " + itf);
            }
        }
        List<String> allParents = ImmutableList.<String>builder().add(superName).addAll(Arrays.asList(interfaces)).build();
        // generate maps for all parent objects
        for (String parentThing : allParents) {
            if (!methodNameMaps.containsKey(parentThing)) {
                findAndMergeSuperMaps(parentThing);
            }
        }
        Map<String, String> methodMap = Maps.<String,String>newHashMap();
        Map<String, String> fieldMap = Maps.<String,String>newHashMap();
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
        //LOGGER.info("Field Maps of " + name + ": " + fieldMap);
    }

    public Set<String> getObfedClasses() {
        return ImmutableSet.copyOf(classNameBiMap.keySet());
    }

    /*public String getStaticFieldType(String oldType, String oldName, String newType, String newName) {
        String fType = getFieldType(oldType, oldName);
        LOGGER.info("Static Field: " + oldType + "+" + oldName + " & " + newType + "+" + newName + " fT: " + fType);
        if (oldType.equals(newType)) {
            return fType;
        }
        Map<String,String> newClassMap = fieldDescriptions.get(newType);
        if (newClassMap == null) {
            newClassMap = Maps.newHashMap();
            fieldDescriptions.put(newType, newClassMap);
        }
        newClassMap.put(newName, fType);
        return fType;
    }*/
}
