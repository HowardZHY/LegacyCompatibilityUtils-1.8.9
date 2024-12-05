package space.libs.asm;

import com.llamalad7.mixinextras.injector.wrapoperation.Dummy;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

@SuppressWarnings("unused")
public class WrapOperationInjectorTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (name.equals("com.llamalad7.mixinextras.injector.wrapoperation.WrapOperationInjector")) {
            try {
                InputStream inputStream = Dummy.class.getResourceAsStream("WrapOperationInjector.class");
                if (inputStream != null) {
                    return IOUtils.toByteArray(inputStream);
                } else {
                    throw new RuntimeException("Cannot get WrapOperationInjector.class");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return bytes;
    }
}
