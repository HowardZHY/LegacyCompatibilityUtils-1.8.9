package space.libs.asm;

import com.llamalad7.mixinextras.injector.wrapoperation.Dummy;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.*;

import java.io.InputStream;

import static org.objectweb.asm.Opcodes.*;

@SuppressWarnings("unused")
public class ClassTransformers implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (name.equals("com.llamalad7.mixinextras.injector.wrapoperation.WrapOperationInjector")) {
            try {
                InputStream inputStream = Dummy.class.getResourceAsStream("WrapOperationInjector.class");
                if (inputStream != null) {
                    return IOUtils.toByteArray(inputStream);
                } else {
                    throw new RuntimeException("Cannot get WrapOperationInjector.class");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return bytes;
            }
        }
        if (name.equals("com.mumfrey.liteloader.core.runtime.Obf")) {
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new LiteLoaderObfVisitor(cw);
            cr.accept(cv, 0);
            return cw.toByteArray();
        }
        if (name.equals("com.mumfrey.liteloader.core.LiteLoaderVersion")) {
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, 0);
            ClassVisitor cv = new LiteLoaderVersionVisitor(cw);
            cr.accept(cv, 0);
            return cw.toByteArray();
        }
        if (name.equals("net.minecraftforge.fml.client.event.ConfigChangedEvent")) {
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new ConfigChangedEventVisitor(cw);
            cr.accept(cv, 0);
            return cw.toByteArray();
        }
        if (name.equals("net.minecraftforge.fml.common.event.FMLModIdMappingEvent")) {
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new FMLModIdMappingEventVisitor(cw);
            cr.accept(cv, 0);
            return cw.toByteArray();
        }
        return bytes;
    }

    public static class LiteLoaderObfVisitor extends ClassVisitor {

        public LiteLoaderObfVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public void visitEnd() {
            // Add the field
            FieldVisitor fv = cv.visitField(ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
                "GuiTextField",
                "Lcom/mumfrey/liteloader/core/runtime/Obf;",
                null,
                null);
            if (fv != null) {
                fv.visitEnd();
            }
            super.visitEnd();
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("<clinit>") && desc.equals("()V")) {
                return new MethodVisitor(Opcodes.ASM5, mv) {
                    @Override
                    public void visitCode() {
                        mv.visitCode();
                    }

                    @Override
                    public void visitInsn(int opcode) {
                        if (opcode == Opcodes.RETURN) {
                            mv.visitTypeInsn(Opcodes.NEW, "com/mumfrey/liteloader/core/runtime/Obf");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitLdcInsn("net.minecraft.client.gui.GuiTextField");
                            mv.visitLdcInsn("avw");
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/mumfrey/liteloader/core/runtime/Obf", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", false);
                            mv.visitFieldInsn(Opcodes.PUTSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "GuiTextField", "Lcom/mumfrey/liteloader/core/runtime/Obf;");
                        }
                        mv.visitInsn(opcode);
                    }
                };
            }
            return mv;
        }
    }

    public static class LiteLoaderVersionVisitor extends ClassVisitor {

        public LiteLoaderVersionVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("isVersionSupported") && desc.equals("(Ljava/lang/String;)Z")) {
                return new MethodVisitor(Opcodes.ASM5, mv) {
                    @Override
                    public void visitCode() {
                        mv.visitCode();
                        mv.visitInsn(Opcodes.ICONST_1); // Push true onto the stack
                        mv.visitInsn(Opcodes.IRETURN);  // Return true
                        mv.visitMaxs(1, 2);
                        mv.visitEnd();
                    }
                };
            }
            return mv;
        }
    }

    /*
     * Copyright (c) Forge Development LLC and contributors
     * SPDX-License-Identifier: LGPL-2.1-only
     */
    public static class ConfigChangedEventVisitor extends ClassVisitor {

        public ConfigChangedEventVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
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
            MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "getModID", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "modID", "Ljava/lang/String;");
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        private void addIsWorldRunning() {
            MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "isWorldRunning", "()Z", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "isWorldRunning", "Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        private void addIsRequiresMcRestart() {
            MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "isRequiresMcRestart", "()Z", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "requiresMcRestart", "Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        private void addGetConfigID() {
            MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "getConfigID", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, "net/minecraftforge/fml/client/event/ConfigChangedEvent", "configID", "Ljava/lang/String;");
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }

    /*
     * Copyright (c) Forge Development LLC and contributors
     * SPDX-License-Identifier: LGPL-2.1-only
     */
    public static class FMLModIdMappingEventVisitor extends ClassVisitor {

        public FMLModIdMappingEventVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
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
}
