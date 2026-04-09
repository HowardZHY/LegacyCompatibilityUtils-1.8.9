package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
public class LoaderVisitor extends ClassVisitor {

    public LoaderVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visitEnd() {
        addGetModClassLoader();
        addFireRemapEvent();
        addFireMissingMappingEvent();
        super.visitEnd();
    }

    private void addGetModClassLoader() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getModClassLoader",
            "()Ljava/lang/ClassLoader;", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "net/minecraftforge/fml/common/Loader", "modClassLoader",
            "Lnet/minecraftforge/fml/common/ModClassLoader;");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private void addFireRemapEvent() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "fireRemapEvent",
            "(Ljava/util/Map;Ljava/util/Map;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "space/libs/util/ForgeUtils", "convertMapKeys", "(Ljava/util/Map;)Ljava/util/Map;", false);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(INVOKESTATIC, "space/libs/util/ForgeUtils", "convertMapKeys", "(Ljava/util/Map;)Ljava/util/Map;", false);
        mv.visitInsn(ICONST_0);
        mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraftforge/fml/common/Loader", "fireRemapEvent", "(Ljava/util/Map;Ljava/util/Map;Z)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(4, 3);
        mv.visitEnd();
    }

    private void addFireMissingMappingEvent() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "fireMissingMappingEvent",
            "(Ljava/util/LinkedHashMap;Ljava/util/LinkedHashMap;ZLnet/minecraftforge/fml/common/registry/GameData;Ljava/util/Map;Ljava/util/Map;)Ljava/util/List;",
            null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "space/libs/util/ForgeUtils", "convertMapKeys", "(Ljava/util/Map;)Ljava/util/Map;", false);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(INVOKESTATIC, "space/libs/util/ForgeUtils", "convertMapKeys", "(Ljava/util/Map;)Ljava/util/Map;", false);
        mv.visitVarInsn(ILOAD, 3);
        mv.visitVarInsn(ALOAD, 5);
        mv.visitMethodInsn(INVOKESTATIC, "space/libs/util/ForgeUtils", "convertMapKeys", "(Ljava/util/Map;)Ljava/util/Map;", false);
        mv.visitVarInsn(ALOAD, 6);
        mv.visitMethodInsn(INVOKESTATIC, "space/libs/util/ForgeUtils", "convertMapKeys", "(Ljava/util/Map;)Ljava/util/Map;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraftforge/fml/common/Loader", "fireMissingMappingEvent", "(Ljava/util/Map;Ljava/util/Map;ZLjava/util/Map;Ljava/util/Map;)Ljava/util/List;", false);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(6, 7);
        mv.visitEnd();
    }
}
