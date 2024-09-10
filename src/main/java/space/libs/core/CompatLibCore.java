package space.libs.core;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import space.libs.util.ModDetector;

import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("all")
@IFMLLoadingPlugin.TransformerExclusions({"space.libs.core", "space.libs.util.cursedmixinextensions", "space.libs.asm"})
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE + 2)
public class CompatLibCore implements IFMLLoadingPlugin {

    public static Logger LOGGER = LogManager.getLogger("CompatLibCore");

    public CompatLibCore() {}

    static {
        MixinBootstrap.init();
        org.spongepowered.asm.mixin.Mixins.addConfiguration("mixins.compatlib.json");
    }

    @Override
    public String[] getASMTransformerClass() {
        ArrayList<String> transformersList = new ArrayList<>();
        ModDetector detector = new ModDetector();
        transformersList.add("space.libs.asm.RemapTransformer");
        transformersList.add("space.libs.asm.ReplaceTransformer");
        transformersList.add("space.libs.asm.FMLTransformer");
        transformersList.add("space.libs.asm.ObfTransformer");

        if (detector.hasSpACore) {
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
        return null;
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
