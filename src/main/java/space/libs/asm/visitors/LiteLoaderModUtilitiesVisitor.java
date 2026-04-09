package space.libs.asm.visitors;

import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

public class LiteLoaderModUtilitiesVisitor extends ClassVisitor {

    public LiteLoaderModUtilitiesVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visitEnd() {
        addGetObfuscatedFieldName1();
        addGetObfuscatedFieldName2();
        super.visitEnd();
    }

    private void addGetObfuscatedFieldName1() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, "getObfuscatedFieldName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(INVOKESTATIC, "com/mumfrey/liteloader/util/ObfuscationUtilities", "getObfuscatedFieldName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();
    }

    private void addGetObfuscatedFieldName2() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, "getObfuscatedFieldName", "(Lcom/mumfrey/liteloader/core/runtime/Obf;)Ljava/lang/String;", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESTATIC, "com/mumfrey/liteloader/util/ObfuscationUtilities", "getObfuscatedFieldName", "(Lcom/mumfrey/liteloader/core/runtime/Obf;)Ljava/lang/String;", false);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }
}
