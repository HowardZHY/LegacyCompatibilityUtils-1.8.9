package space.libs.core;

import com.llamalad7.mixinextras.injector.wrapoperation.Dummy;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.mixin.Mixins;
import space.libs.util.ModDetector;

import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("unused")
@IFMLLoadingPlugin.TransformerExclusions({"space.libs.asm", "space.libs.core", "space.libs.util.cursedmixinextensions"})
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE + 2)
public class CompatLibCore implements IFMLLoadingPlugin, ICoreUtils {

    public static Dummy DUMMY;

    public CompatLibCore() {
        classLoader.registerTransformer("space.libs.asm.ClassTransformers");
    }

    static {
        //DUMMY = new Dummy();
        CompatLibCoreConfig.init();
        LOGGER.info("Initializing CompatLib Core Class...");
    }

    @SuppressWarnings("unchecked")
    @Override
    public String[] getASMTransformerClass() {
        Mixins.addConfiguration("mixins.compatlib.json");
        try {
            Field f = LaunchClassLoader.class.getDeclaredField("transformerExceptions");
            f.setAccessible(true);
            Set<String> exs = (Set<String>) f.get(classLoader);
            exs.remove("com.llamalad7.mixinextras.");
            exs.add("com.llamalad7.mixinextras.wrapper.");
            f.set(classLoader, exs);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        ArrayList<String> transformersList = new ArrayList<>();
        ModDetector.init();
        transformersList.add("space.libs.asm.DefaultCompatTransformer");
        transformersList.add("space.libs.asm.ReplaceTransformer");
        transformersList.add("space.libs.asm.LegacyObfTransformer");
        transformersList.addAll(ModDetector.addEarlyTransformers());
        String[] transformers = new String[transformersList.size()];
        return transformersList.toArray(transformers);
    }

    @Override
    public String getModContainerClass() {
        return "space.libs.core.CompatLibCoreContainer";
    }

    @Override
    public String getSetupClass() {
        return "space.libs.core.CompatLibSetupHook";
    }

    @Override
    public void injectData(Map<String, Object> data) {
        ModDetector.onInjectData(data);
    }

    @Override
    public String getAccessTransformerClass() {
        if (ModDetector.hasSpACore) {
            return "net.specialattack.forge.core.asm.SpACoreAccessTransformer";
        }
        return null;
    }
}
