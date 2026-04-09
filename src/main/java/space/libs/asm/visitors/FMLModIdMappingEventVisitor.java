package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
public class FMLModIdMappingEventVisitor extends ClassVisitor {

    public FMLModIdMappingEventVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visitEnd() {
        addNewConstructor();
        super.visitEnd();
    }

    private void addNewConstructor() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(
            INVOKESTATIC,
            "space/libs/util/ForgeUtils",
            "convertMapKeys",
            "(Ljava/util/Map;)Ljava/util/Map;",
            false
        );
        mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(
            INVOKESTATIC,
            "space/libs/util/ForgeUtils",
            "convertMapKeys",
            "(Ljava/util/Map;)Ljava/util/Map;",
            false
        );
        mv.visitInsn(ICONST_0);
        mv.visitMethodInsn(INVOKESPECIAL, "net/minecraftforge/fml/common/event/FMLModIdMappingEvent", "<init>", "(Ljava/util/Map;Ljava/util/Map;Z)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(4, 3);
        mv.visitEnd();
    }
}
