package space.libs.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import space.libs.asm.remap.DefaultRemapRemapper;

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
        reader.accept(new DefaultRemapRemapper.RemappingAdapter(writer, new DefaultRemapRemapper()), ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
    }
}
