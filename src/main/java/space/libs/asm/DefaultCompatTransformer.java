package space.libs.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;
import space.libs.asm.remap.CustomRemappingAdapter;

@SuppressWarnings("all")
public class DefaultCompatTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (ClassNameList.StartsWith(name) || ClassNameList.Contains(name)) {
            return bytes;
        }
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        reader.accept(CustomRemappingAdapter.Default(writer), ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
    }
}
