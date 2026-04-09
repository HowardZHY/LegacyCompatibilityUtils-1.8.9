package space.libs.asm;

import com.llamalad7.mixinextras.injector.wrapoperation.Dummy;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.*;
import space.libs.asm.visitors.*;
import space.libs.core.ICoreUtils;

import java.io.InputStream;

@SuppressWarnings("unused")
public class ClassTransformers implements IClassTransformer, ICoreUtils {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (name.endsWith("$DepLoadInst")) {
            LOGGER.info("Detected Legacy DepLoader: " + name);
            return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, DepLoadInstVisitor.class, 0);
        } else {
            if (name.equals("cc.polyfrost.oneconfig.internal.config.core.ConfigCore")) {
                LOGGER.info("Try Optimize OneConfig-v0 Saving...");
                return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_FRAMES, ConfigCoreVisitor.class, 0);
            } else if (name.startsWith("com")) {
                switch (name) {
                    case "com.llamalad7.mixinextras.injector.wrapoperation.WrapOperationInjector":
                        try {
                            InputStream inputStream = Dummy.class.getResourceAsStream("WrapOperationInjector.class");
                            if (inputStream != null) {
                                return IOUtils.toByteArray(inputStream);
                            } else {
                                throw new RuntimeException("Cannot get WrapOperationInjector.class");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return bytes;
                        }
                    case "com.mumfrey.liteloader.core.runtime.Obf": {
                        return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, LiteLoaderObfVisitor.class, 0);
                    }
                    case "com.mumfrey.liteloader.core.LiteLoaderVersion": {
                        return TransformerUtils.transform(bytes, 0, LiteLoaderVersionVisitor.class, 0);
                    }
                    case "com.mumfrey.liteloader.util.ModUtilities" : {
                        return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, LiteLoaderModUtilitiesVisitor.class, 0);
                    }
                    default: {
                        return bytes;
                    }
                }
            } else if (name.startsWith("net")) {
                if (name.startsWith("net.minecraftfor")) {
                    switch (name) {
                        case "net.minecraftforge.fml.common.Loader": {
                            return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, LoaderVisitor.class, 0);
                        }
                        case "net.minecraftforge.fml.common.versioning.VersionRange": {
                            return TransformerUtils.transform(bytes, 0, VersionRangeVisitor.class, 0);
                        }
                        case "net.minecraftforge.fml.client.event.ConfigChangedEvent": {
                            return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, ConfigChangedEventVisitor.class, 0);
                        }
                        case "net.minecraftforge.fml.common.event.FMLModIdMappingEvent": {
                            return TransformerUtils.transform(bytes, ClassWriter.COMPUTE_MAXS, FMLModIdMappingEventVisitor.class, 0);
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

}
