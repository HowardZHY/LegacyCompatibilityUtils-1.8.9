package space.libs.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import space.libs.asm.remap.RemapperUtils;

@SuppressWarnings("all")
public class DefaultCompatTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        return RemapperUtils.transform(name, transformedName, bytes);
    }
}
