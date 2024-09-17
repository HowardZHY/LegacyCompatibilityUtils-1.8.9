package space.libs.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

@SuppressWarnings("unused")
public class LiteLoaderObfTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (name.equals("com.mumfrey.liteloader.core.runtime.Obf")) {
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, 0);
            ClassVisitor cv = new ObfVisitor(cw);
            cr.accept(cv, 0);
            return cw.toByteArray();
        }
        return bytes;
    }

    public static class ObfVisitor extends ClassVisitor {

        public ObfVisitor(ClassVisitor cv) {
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
                            mv.visitLdcInsn("bul");
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
}
