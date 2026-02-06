/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */

package space.libs.asm;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.common.patcher.ClassPatchManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import space.libs.core.CompatLibDebug;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class CustomRemapRemapper extends SrgRemapper {

    private LaunchClassLoader classLoader;

    public static final Logger LOGGER = LogManager.getLogger();

    public static boolean DEBUG_REMAPPING = CompatLibDebug.DEBUG_REMAP;

    public CustomRemapRemapper() {
    }

    public CustomRemapRemapper(String name) {
        this.setup((LaunchClassLoader) this.getClass().getClassLoader(), name);
    }

    public void setup(LaunchClassLoader classLoader, String deobfFileName) {
        this.classLoader = classLoader;
        try {
            loadMappings(deobfFileName);
        } catch (Exception e) {
            LOGGER.error("An error occurred loading the custom map data" + e);
        }
    }

    public boolean isRemappedClass(String className) {
        return !map(className).equals(className);
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

    @Override
    protected String getFieldType(String owner, String name) {
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
                Map<String, String> resMap = Maps.newHashMap();
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

    @Override
    public Map<String, String> getFieldMap(String className) {
        Map<String, String> result = super.getFieldMap(className);
        if (DEBUG_REMAPPING && !className.startsWith("java")) {
            LOGGER.info("Field map for " + className + " : " + result);
        }
        return result;
    }

    @Override
    public Map<String, String> getMethodMap(String className) {
        Map<String, String> result = super.getMethodMap(className);
        if (DEBUG_REMAPPING && !className.startsWith("java")) {
            // LOGGER.info("Method map for " + className + " : " + result);
        }
        return result;
    }

    @Override
    protected SuperInfo getSuperInfo(String name) {
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
            return new SuperInfo(getLegacyName(superName), legacyInterfaces);
        } catch (Exception e) {
            LOGGER.error("Failed reading super info for " + name, e);
            return null;
        }
    }

    @Override
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
        super.mergeSuperMaps(name, superName, interfaces);
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
