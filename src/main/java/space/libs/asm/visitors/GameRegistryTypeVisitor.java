package space.libs.asm.visitors;

import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

public class GameRegistryTypeVisitor extends ClassVisitor {

    public GameRegistryTypeVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visitEnd() {
        addGetRegistry();
        super.visitEnd();
    }

    private void addGetRegistry() {
        MethodVisitor mv = cv.visitMethod(
            ACC_PUBLIC,
            "getRegistry",
            "()Lnet/minecraftforge/fml/common/registry/FMLControlledNamespacedRegistry;",
            "()Lnet/minecraftforge/fml/common/registry/FMLControlledNamespacedRegistry<*>;",
            null
        );
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(
            INVOKESTATIC,
            "space/libs/util/forge/ForgeUtils",
            "getRegistry",
            "(Lnet/minecraftforge/fml/common/registry/GameRegistry$Type;)Lnet/minecraftforge/fml/common/registry/FMLControlledNamespacedRegistry;",
            false
        );
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }
}
