package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM5;

public class DepLoadInstVisitor extends ClassVisitor {

    public DepLoadInstVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("searchCoreMod")) {
            return new MethodVisitor(ASM5, mv) {
                @Override
                public void visitLdcInsn(Object cst) {
                    String s = cst.toString();
                    if (s.equals("loadedCoremods")) {
                        super.visitLdcInsn("ignoredModFiles");
                        return;
                    } else if (s.equals("reparsedCoremods")) {
                        super.visitLdcInsn("candidateModFiles");
                        return;
                    }
                    super.visitLdcInsn(cst);
                }
            };
        }
        return mv;
    }
}
