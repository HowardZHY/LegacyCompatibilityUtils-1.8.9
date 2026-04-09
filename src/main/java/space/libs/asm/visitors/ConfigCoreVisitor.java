package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class ConfigCoreVisitor extends ClassVisitor {

    public ConfigCoreVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("<clinit>")) {
            return new MethodVisitor(ASM5, mv) {
                @Override
                public void visitCode() {
                    super.visitCode();
                    visitTypeInsn(NEW, "java/util/ArrayList");
                    visitInsn(DUP);
                    visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
                    visitFieldInsn(PUTSTATIC, "cc/polyfrost/oneconfig/internal/config/core/ConfigCore", "mods", "Ljava/util/List;");
                    visitTypeInsn(NEW, "java/util/HashMap");
                    visitInsn(DUP);
                    visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
                    visitFieldInsn(PUTSTATIC, "cc/polyfrost/oneconfig/internal/config/core/ConfigCore", "subMods", "Ljava/util/HashMap;");
                    visitInsn(RETURN);
                    visitMaxs(2, 0);
                    visitEnd();
                }
            };
        }
        return mv;
    }
}
