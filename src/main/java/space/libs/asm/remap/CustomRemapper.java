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
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.common.patcher.ClassPatchManager;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.util.*;

@SuppressWarnings({"DataFlowIssue", "UnstableApiUsage"})
public class CustomRemapper extends DefaultRemapper {

    public CustomRemapper(String name) {
        super(name, true);
    }

    @Override
    protected void setup() {
        try {
            CharSource srgSource = Resources.asCharSource(this.mappings, Charsets.UTF_8);
            List<String> srgList = srgSource.readLines();
            Splitter splitter = Splitter.on(CharMatcher.anyOf(": ")).omitEmptyStrings().trimResults();
            for (String line : srgList) {
                String[] parts = Iterables.toArray(splitter.split(line),String.class);
                String typ = parts[0];
                if ("CL".equals(typ)) {
                    this.classesIn.put(parts[1],parts[2]);
                }
                else if ("MD".equals(typ)) {
                    parseMethod(parts);
                }
                else if ("FD".equals(typ)) {
                    parseField(parts);
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred loading the custom map data" + e);
        }
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
            rawFieldMaps.put(cl, Maps.newHashMap());
        }
        String typed = getFieldType(cl, oldName);
        if (!"null".equals(typed)) {
            rawFieldMaps.get(cl).put(oldName + ":" + typed, newName);
        }
        rawFieldMaps.get(cl).put(oldName + ":null", newName);
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
            rawMethodMaps.put(cl, Maps.newHashMap());
        }
        rawMethodMaps.get(cl).put(oldName+sig, newName);
    }

    public String getRealName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return name;
        }
        if (name.contains("/")) {
            return name; // Not mapped by Forge
        }
        String mappedName = this.map(name);
        String realName = FMLDeobfuscatingRemapper.INSTANCE.unmap(mappedName);
        if (DEBUG_REMAPPING && (!name.equals(realName))) {
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
        return this.unmap(mappedName);
    }

    @Override
    protected String getFieldType(String owner, String name) {
        if (fieldDescriptions.containsKey(owner)) {
            return fieldDescriptions.get(owner).get(name);
        }
        synchronized (fieldDescriptions) {
            byte[] classBytes = this.getBytes(map(owner).replace('/', '.'));
            if (classBytes == null) {
                return "null";
            }
            ClassReader cr = new ClassReader(classBytes);
            ClassNode classNode = new ClassNode();
            cr.accept(classNode, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            Map<String, String> resMap = Maps.newHashMap();
            for (FieldNode fieldNode : classNode.fields) {
                resMap.put(fieldNode.name, fieldNode.desc);
            }
            fieldDescriptions.put(owner, resMap);
            return resMap.get(name);
        }
    }

    @Override
    public String mapFieldName(String owner, String name, String desc) {
        if (this.noClasses()) {
            return name;
        }
        Map<String, String> fields = getFieldMap(owner);
        if (fields != null && fields.containsKey(name + ":" + desc)) {
            return fields.get(name + ":" + desc);
        } else {
            try {
                if (fields.get(name + ":null") != null) {
                    if (DEBUG_REMAPPING && (!owner.contains("/") || owner.contains("net"))) {
                        LOGGER.info("Try map field without desc " + owner + "." + name + " to " + fields.get(name + ":null"));
                    }
                    return fields.get(name + ":null");
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
    public void loadSuperMaps(String name) {
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

    @Override
    public void mergeSuperMaps(String name, String superName, String[] interfaces) {
        if (this.noClasses()) {
            return;
        }
        if (Strings.isNullOrEmpty(superName)) {
            return;
        }
        if (DEBUG_REMAPPING && (!superName.startsWith("java") || !name.startsWith("java"))) {
            LOGGER.info("Computing super maps for " + name + " & " + superName);
            LOGGER.info("Interfaces: " + Arrays.toString(interfaces));
        }
        List<String> allParents = ImmutableList.<String>builder().add(superName).addAll(Arrays.asList(interfaces)).build();
        // generate maps for all parent objects
        for (String parentThing : allParents) {
            if (!methodsMap.containsKey(parentThing)) {
                loadSuperMaps(parentThing);
            }
        }
        Map<String, String> fields = Maps.newHashMap();
        Map<String, String> methods = Maps.newHashMap();
        for (String parentThing : allParents) {
            if (fieldsMap.containsKey(parentThing)) {
                fields.putAll(fieldsMap.get(parentThing));
            }
            if (methodsMap.containsKey(parentThing)) {
                methods.putAll(methodsMap.get(parentThing));
            }
        }
        if (rawFieldMaps.containsKey(name)) {
            fields.putAll(rawFieldMaps.get(name));
        }
        if (rawMethodMaps.containsKey(name)) {
            methods.putAll(rawMethodMaps.get(name));
        }
        fieldsMap.put(name, ImmutableMap.copyOf(fields));
        methodsMap.put(name, ImmutableMap.copyOf(methods));
        //LOGGER.info("Field Maps of " + name + ": " + fields);
    }

    @SuppressWarnings("unused")
    public Set<String> getObfedClasses() {
        return ImmutableSet.copyOf(classesBiMap.keySet());
    }
}
