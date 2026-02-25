/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */

package space.libs.asm.remap;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.launchwrapper.IClassNameTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.IOException;
import java.util.Map;

public class DefaultRemapper extends RemapperBase implements IClassNameTransformer {

    public final LaunchClassLoader classLoader;

    public DefaultRemapper(final String file, final Boolean deobfuscating) {
        super(file, deobfuscating);
        this.classLoader = (LaunchClassLoader) this.getClass().getClassLoader();
    }

    @Override
    public String remapClassName(String typeName) {
        return map(typeName.replace('.', '/')).replace('/', '.');
    }

    @Override
    public String unmapClassName(String typeName) {
        return unmap(typeName.replace('.', '/')).replace('/', '.');
    }

    @Override
    protected String getFieldType(String owner, String name) {
        Map<String, String> fieldDescriptions = this.fieldDescriptions.get(owner);
        if (fieldDescriptions != null) {
            return fieldDescriptions.get(name);
        }
        byte[] bytes = getBytes(owner);
        if (bytes == null) {
            return null;
        }
        ClassReader reader = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        String result = null;
        fieldDescriptions = Maps.newHashMapWithExpectedSize(classNode.fields.size());
        for (FieldNode fieldNode : classNode.fields) {
            fieldDescriptions.put(fieldNode.name, fieldNode.desc);
            if (fieldNode.name.equals(name)) {
                result = fieldNode.desc;
            }
        }
        this.fieldDescriptions.put(owner, fieldDescriptions);
        return result;
    }

    @Override
    public void loadSuperMaps(String name) {
        byte[] bytes = getBytes(name);
        if (bytes != null) {
            ClassReader reader = new ClassReader(bytes);
            mergeSuperMaps(name, reader.getSuperName(), reader.getInterfaces());
        }
    }

    @Override
    public void mergeSuperMaps(String name, String superName, String[] interfaces) {
        if (Strings.isNullOrEmpty(superName)) {
            return;
        }
        String[] parents = new String[interfaces.length + 1];
        parents[0] = superName;
        System.arraycopy(interfaces, 0, parents, 1, interfaces.length);
        for (String parent : parents) {
            if (!this.fieldsMap.containsKey(parent)) {
                loadSuperMaps(parent);
            }
        }
        Map<String, String> fields = Maps.newHashMap();
        Map<String, String> methods = Maps.newHashMap();

        Map<String, String> m;
        for (String parent : parents) {
            m = this.fieldsMap.get(parent);
            if (m != null) {
                fields.putAll(m);
            }
            m = this.methodsMap.get(parent);
            if (m != null) {
                methods.putAll(m);
            }
        }
        fields.putAll(this.rawFields.row(name));
        methods.putAll(this.rawMethods.row(name));
        this.fieldsMap.put(name, ImmutableMap.copyOf(fields));
        this.methodsMap.put(name, ImmutableMap.copyOf(methods));
    }

    protected byte[] getBytes(String name) {
        try {
            return Launch.classLoader.getClassBytes(name);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}
