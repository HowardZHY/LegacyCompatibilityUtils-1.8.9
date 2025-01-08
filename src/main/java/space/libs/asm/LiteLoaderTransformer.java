package space.libs.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

@SuppressWarnings("unused")
public class LiteLoaderTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
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
        return bytes;
    }

    public static class LiteLoaderObfVisitor extends ClassVisitor {

        public LiteLoaderObfVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public void visitEnd() {
            // Add the field
            FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
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
}
