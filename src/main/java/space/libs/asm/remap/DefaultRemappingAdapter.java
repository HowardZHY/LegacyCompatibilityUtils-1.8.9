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

package space.libs.asm.remap;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;

public class DefaultRemappingAdapter extends RemappingClassAdapter {

    public static String DEFAULT_MAPPINGS = "compatlib.srg";

    public static DefaultRemapper INSTANCE;

    public DefaultRemappingAdapter(ClassVisitor cv, DefaultRemapper instance) {
        super(cv, instance);
        INSTANCE = instance;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if (interfaces == null) {
            interfaces = new String[0];
        }
        INSTANCE.mergeSuperMaps(name, superName, interfaces);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    protected MethodVisitor createRemappingMethodAdapter(int access, String newDesc, MethodVisitor mv) {
        return new RemappingMethodAdapter(access, newDesc, mv, DefaultRemappingAdapter.this.remapper) {
            @Override
            public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                String type = this.remapper.mapType(owner);
                String fieldName = this.remapper.mapFieldName(owner, name, desc);
                String newDesc = this.remapper.mapDesc(desc);
                if ((opcode == Opcodes.GETSTATIC) && type.startsWith("net/minecraft/") && newDesc.startsWith("Lnet/minecraft/")) {
                    String replDesc = INSTANCE.getStaticFieldType(owner, name, type, fieldName);
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
