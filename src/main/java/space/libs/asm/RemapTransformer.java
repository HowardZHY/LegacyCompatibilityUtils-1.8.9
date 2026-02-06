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

import net.minecraft.launchwrapper.IClassNameTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.RemappingClassAdapter;
import org.objectweb.asm.commons.RemappingMethodAdapter;
import com.google.common.collect.Maps;

import java.util.Map;

@SuppressWarnings("all")
public class RemapTransformer extends SrgRemapper implements IClassTransformer, IClassNameTransformer {

    public static String DEFAULT_MAPPINGS = "compatlib.srg";

    public RemapTransformer() throws Exception {
        loadMappings(DEFAULT_MAPPINGS);
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (ClassNameList.StartsWith(name)) {
            return bytes;
        }
        if (ClassNameList.Contains(name)) {
            return bytes;
        }

        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        reader.accept(new RemappingAdapter(writer), ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
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
    protected boolean shouldAddNullFieldMapping(String fieldType) {
        return fieldType != null;
    }

    @SuppressWarnings("all")
    public String getStaticFieldType(String oldType, String oldName, String newType, String newName) {
        String type = getFieldType(oldType, oldName);
        if (oldType.equals(newType)) {
            return type;
        }
        Map<String, String> newClassMap = this.fieldDescriptions.get(newType);
        if (newClassMap == null) {
            newClassMap = Maps.newHashMap();
            this.fieldDescriptions.put(newType, newClassMap);
        }
        newClassMap.put(newName, type);
        return type;
    }

    private class RemappingAdapter extends RemappingClassAdapter {

        public RemappingAdapter(ClassVisitor cv) {
            super(cv, RemapTransformer.this);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            if (interfaces == null) {
                interfaces = ArrayUtils.EMPTY_STRING_ARRAY;
            }
            mergeSuperMaps(name, superName, interfaces);
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        protected MethodVisitor createRemappingMethodAdapter(int access, String newDesc, MethodVisitor mv) {
            return new RemappingMethodAdapter(access, newDesc, mv, RemappingAdapter.this.remapper) {

                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                    String type = this.remapper.mapType(owner);
                    String fieldName = this.remapper.mapFieldName(owner, name, desc);
                    String newDesc = this.remapper.mapDesc(desc);
                    if ((opcode == Opcodes.GETSTATIC) && type.startsWith("net/minecraft/") && newDesc.startsWith("Lnet/minecraft/")) {
                        String replDesc = getStaticFieldType(owner, name, type, fieldName);
                        if (replDesc != null) {
                            newDesc = this.remapper.mapDesc(replDesc);
                        }
                    }
                    if (this.mv != null) {
                        this.mv.visitFieldInsn(opcode, type, fieldName, newDesc);
                    }
                }
            };
        }
    }

}
