package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class LiteLoaderVersionVisitor extends ClassVisitor {

    public LiteLoaderVersionVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("isVersionSupported") && desc.equals("(Ljava/lang/String;)Z")) {
            return new MethodVisitor(ASM5, mv) {
                @Override
                public void visitCode() {
                    mv.visitCode();
                    mv.visitInsn(ICONST_1);
                    mv.visitInsn(IRETURN);
                    mv.visitMaxs(1, 2);
                    mv.visitEnd();
                }
            };
        }
        return mv;
    }
}
