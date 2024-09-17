package space.libs.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

@SuppressWarnings("unused")
public class LiteLoaderVersionTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
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
