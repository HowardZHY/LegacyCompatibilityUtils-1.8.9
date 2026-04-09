package space.libs.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class VersionRangeVisitor extends ClassVisitor {

    public VersionRangeVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("containsVersion")) {
            return new MethodVisitor(ASM5, mv) {
                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                    if (opcode == INVOKEVIRTUAL && "net/minecraftforge/fml/common/versioning/Restriction".equals(owner) &&
                        "containsVersion".equals(name) && "(Lnet/minecraftforge/fml/common/versioning/ArtifactVersion;)Z".equals(descriptor)) {
                        super.visitMethodInsn(INVOKESTATIC, "space/libs/util/ForgeUtils", "checkVersion",
                            "(Lnet/minecraftforge/fml/common/versioning/Restriction;Lnet/minecraftforge/fml/common/versioning/ArtifactVersion;)Z", false);
                    } else {
                        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                    }
                }
            };
        }
        return mv;
    }
}
