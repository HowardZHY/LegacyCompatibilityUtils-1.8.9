package space.libs.core;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.ILateMixinLoader;
import zone.rong.mixinbooter.MixinLoader;

import java.util.ArrayList;
import java.util.List;

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

    }
}
