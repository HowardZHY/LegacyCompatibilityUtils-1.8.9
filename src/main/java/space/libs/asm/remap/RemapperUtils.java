package space.libs.asm.remap;

import org.objectweb.asm.*;
import space.libs.asm.*;

@SuppressWarnings("unused")
public class RemapperUtils {

    public static byte[] transform(String name, String transformedName, byte[] bytes) {
        if (ClassNameList.StartsWithBlacklist(name) || ClassNameList.ContainsBlacklist(name)) {
            bytes = transformRemap(bytes, true);
        } else if (ClassNameList.ShouldNotTransform(name)) {
            return bytes;
        }
        return transformRemap(bytes, false);
    }

    public static byte[] transformRemap(byte[] bytes, boolean legacy) {
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        reader.accept(legacy ? CustomRemappingAdapter.Legacy(writer) : CustomRemappingAdapter.Default(writer), ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
    }
}
