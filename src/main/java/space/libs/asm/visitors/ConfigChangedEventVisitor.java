package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
public class ConfigChangedEventVisitor extends ClassVisitor {

    public ConfigChangedEventVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visitEnd() {
        addGetModID();
        addIsWorldRunning();
        addIsRequiresMcRestart();
        addGetConfigID();
        super.visitEnd();
    }

    private void addGetModID() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getModID", "()Ljava/lang/String;", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "modID", "Ljava/lang/String;");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void addIsWorldRunning() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "isWorldRunning", "()Z", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "isWorldRunning", "Z");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void addIsRequiresMcRestart() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "isRequiresMcRestart", "()Z", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "requiresMcRestart", "Z");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void addGetConfigID() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getConfigID", "()Ljava/lang/String;", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "configID", "Ljava/lang/String;");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
}
