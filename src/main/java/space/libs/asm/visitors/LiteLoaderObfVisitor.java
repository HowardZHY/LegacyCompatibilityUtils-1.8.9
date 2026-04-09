package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class LiteLoaderObfVisitor extends ClassVisitor {

    public LiteLoaderObfVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visitEnd() {
        FieldVisitor fv = cv.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL,
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
        if (name.equals("<clinit>")) {
            return new MethodVisitor(ASM5, mv) {
                @Override
                public void visitInsn(int opcode) {
                    if (opcode == RETURN) {
                        mv.visitTypeInsn(NEW, "com/mumfrey/liteloader/core/runtime/Obf");
                        mv.visitInsn(DUP);
                        mv.visitLdcInsn("net.minecraft.client.gui.GuiTextField");
                        mv.visitLdcInsn("avw");
                        mv.visitMethodInsn(INVOKESPECIAL, "com/mumfrey/liteloader/core/runtime/Obf", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", false);
                        mv.visitFieldInsn(PUTSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "GuiTextField", "Lcom/mumfrey/liteloader/core/runtime/Obf;");

                        mv.visitFieldInsn(GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "obfs", "Ljava/util/Map;");
                        mv.visitLdcInsn("GuiTextField");
                        mv.visitFieldInsn(GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "GuiTextField", "Lcom/mumfrey/liteloader/core/runtime/Obf;");
                        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
                        mv.visitInsn(POP);

                        mv.visitFieldInsn(GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "obfs", "Ljava/util/Map;");
                        mv.visitLdcInsn("${GuiTextField}");
                        mv.visitFieldInsn(GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "GuiTextField", "Lcom/mumfrey/liteloader/core/runtime/Obf;");
                        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
                        mv.visitInsn(POP);
                    }
                    mv.visitInsn(opcode);
                }
            };
        }
        return mv;
    }
}
