package space.libs.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;
import space.libs.core.CompatLibCore;

import static org.objectweb.asm.Opcodes.*;

@SuppressWarnings("unused")
public class ClassTransformers implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (name.endsWith("$DepLoadInst")) {
            CompatLibCore.LOGGER.info("Detected Legacy DepLoader: " + name);
            return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, DepLoadInstVisitor.class, 0);
        } else {
            if (name.equals("cc.polyfrost.oneconfig.internal.config.core.ConfigCore")) {
                CompatLibCore.LOGGER.info("Try Optimize OneConfig-v0 Saving...");
                return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_FRAMES, ConfigCoreVisitor.class, 0);
            } else if (name.startsWith("com")) {
                switch (name) {
                    case "com.mumfrey.liteloader.core.runtime.Obf": {
                        return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, LiteLoaderObfVisitor.class, 0);
                    }
                    case "com.mumfrey.liteloader.core.LiteLoaderVersion": {
                        return TransformerUtils.transform(bytes, 0, LiteLoaderVersionVisitor.class, 0);
                    }
                    default: {
                        return bytes;
                    }
                }
            } else if (name.startsWith("net")) {
                if (name.startsWith("net.minecraftfor")) {
                    switch (name) {
                        case "net.minecraftforge.fml.common.versioning.VersionRange": {
                            return TransformerUtils.transform(bytes, 0, VersionRangeVisitor.class, 0);
                        }
                        default: {
                            return bytes;
                        }
                    }
                }
            }
        }
        return bytes;
    }

    public static class ConfigCoreVisitor extends ClassVisitor {

        public ConfigCoreVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("<clinit>")) {
                return new MethodVisitor(Opcodes.ASM5, mv) {
                    @Override
                    public void visitCode() {
                        super.visitCode();
                        visitTypeInsn(Opcodes.NEW, "java/util/ArrayList");
                        visitInsn(Opcodes.DUP);
                        visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
                        visitFieldInsn(Opcodes.PUTSTATIC, "cc/polyfrost/oneconfig/internal/config/core/ConfigCore", "mods", "Ljava/util/List;");
                        visitTypeInsn(Opcodes.NEW, "java/util/HashMap");
                        visitInsn(Opcodes.DUP);
                        visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
                        visitFieldInsn(Opcodes.PUTSTATIC, "cc/polyfrost/oneconfig/internal/config/core/ConfigCore", "subMods", "Ljava/util/HashMap;");
                        visitInsn(Opcodes.RETURN);
                        visitMaxs(2, 0);
                        visitEnd();
                    }
                };
            }
            return mv;
        }
    }

    public static class DepLoadInstVisitor extends ClassVisitor {

        public DepLoadInstVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("searchCoreMod")) {
                return new MethodVisitor(Opcodes.ASM5, mv) {
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

    public static class LiteLoaderObfVisitor extends ClassVisitor {

        public LiteLoaderObfVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public void visitEnd() {
            FieldVisitor fv = cv.visitField(ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
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
                return new MethodVisitor(Opcodes.ASM5, mv) {
                    @Override
                    public void visitInsn(int opcode) {
                        if (opcode == Opcodes.RETURN) {
                            mv.visitTypeInsn(Opcodes.NEW, "com/mumfrey/liteloader/core/runtime/Obf");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitLdcInsn("net.minecraft.client.gui.GuiTextField");
                            mv.visitLdcInsn("avw");
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/mumfrey/liteloader/core/runtime/Obf", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", false);
                            mv.visitFieldInsn(Opcodes.PUTSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "GuiTextField", "Lcom/mumfrey/liteloader/core/runtime/Obf;");

                            mv.visitFieldInsn(Opcodes.GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "obfs", "Ljava/util/Map;");
                            mv.visitLdcInsn("GuiTextField");
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "GuiTextField", "Lcom/mumfrey/liteloader/core/runtime/Obf;");
                            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
                            mv.visitInsn(Opcodes.POP);

                            mv.visitFieldInsn(Opcodes.GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "obfs", "Ljava/util/Map;");
                            mv.visitLdcInsn("${GuiTextField}");
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "com/mumfrey/liteloader/core/runtime/Obf", "GuiTextField", "Lcom/mumfrey/liteloader/core/runtime/Obf;");
                            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
                            mv.visitInsn(Opcodes.POP);
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
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.IRETURN);
                        mv.visitMaxs(1, 2);
                        mv.visitEnd();
                    }
                };
            }
            return mv;
        }
    }

    public static class VersionRangeVisitor extends ClassVisitor {

        public VersionRangeVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("containsVersion")) {
                return new MethodVisitor(Opcodes.ASM5, mv) {
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
}
