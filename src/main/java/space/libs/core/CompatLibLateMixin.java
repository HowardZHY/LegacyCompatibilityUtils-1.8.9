package space.libs.core;

import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.mixin.Mixins;
import space.libs.util.ModDetector;
import zone.rong.mixinbooter.ILateMixinLoader;
import zone.rong.mixinbooter.MixinLoader;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@MixinLoader
public class CompatLibLateMixin implements ILateMixinLoader {

    public CompatLibLateMixin() {
        CompatLibCore.LOGGER.info("Loading Late Mixins for mods...");
        Mixins.addConfiguration("mixins.compatlib.mods.json");
        loadModTransformers();
    }

    @Override
    public List<String> getMixinConfigs() {
        List<String> mixins = new ArrayList<>();
        mixins.add("mixins.compatlib.mods.json");
        return mixins;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return true;
    }

    public static void loadModTransformers() {
        if (ModDetector.hasAlexIILLib) {
            CompatLibCore.LOGGER.info("Found AlexIILLib, load ASM Transformers of it.");
            Launch.classLoader.registerTransformer("alexiil.mods.lib.coremod.ClassTransformer");
            if (ModDetector.hasCivCraft) {
                CompatLibCore.LOGGER.info("Found CivCraft, load ASM Transformers of it.");
                Launch.classLoader.registerTransformer("alexiil.mods.civ.coremod.CivCraftTransformer");
            }
        }
    }
}
