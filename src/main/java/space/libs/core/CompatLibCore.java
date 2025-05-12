package space.libs.core;

import com.llamalad7.mixinextras.injector.wrapoperation.Dummy;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;
import space.libs.util.ModDetector;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
@IFMLLoadingPlugin.TransformerExclusions({"space.libs.core", "space.libs.util.cursedmixinextensions", "space.libs.asm"})
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE + 2)
public class CompatLibCore implements IFMLLoadingPlugin {

    public static final Logger LOGGER = LogManager.getLogger("CompatLibCore");

    public static Dummy DUMMY;

    public CompatLibCore() {
        Launch.classLoader.registerTransformer("space.libs.asm.ClassTransformers");
    }

    static {
        //DUMMY = new Dummy();
        LOGGER.info("Initializing CompatLib Core Class...");
    }

    @SuppressWarnings("unchecked")
    @Override
    public String[] getASMTransformerClass() {
        Mixins.addConfiguration("mixins.compatlib.json");
        try {
            Field f = LaunchClassLoader.class.getDeclaredField("transformerExceptions");
            f.setAccessible(true);
            Set<String> exs = (Set<String>) f.get(Launch.classLoader);
            exs.remove("com.llamalad7.mixinextras.");
            exs.add("com.llamalad7.mixinextras.wrapper.");
            f.set(Launch.classLoader, exs);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        ArrayList<String> transformersList = new ArrayList<>();
        ModDetector.init();
        transformersList.add("space.libs.asm.RemapTransformer");
        transformersList.add("space.libs.asm.ReplaceTransformer");
        transformersList.add("space.libs.asm.LegacyObfTransformer");

        if (ModDetector.hasSpACore) {
            LOGGER.info("Found SpACore, load ASM Transformers of it.");
            transformersList.add("net.specialattack.forge.core.asm.SpACoreModTransformer");
            transformersList.add("net.specialattack.forge.core.asm.SpACoreHookTransformer");
        }

        String[] transformers = new String[transformersList.size()];
        return transformersList.toArray(transformers);
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return "space.libs.core.CompatLibSetupHook";
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        if (ModDetector.hasSpACore) {
            return "net.specialattack.forge.core.asm.SpACoreAccessTransformer";
        }
        return null;
    }
}
