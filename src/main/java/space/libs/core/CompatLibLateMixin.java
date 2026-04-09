package space.libs.core;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.ILateMixinLoader;
import zone.rong.mixinbooter.MixinLoader;

import java.util.*;

@MixinLoader
public class CompatLibLateMixin implements ILateMixinLoader, ICoreUtils {

    public CompatLibLateMixin() {
        LOGGER.info("Loading Late Mixins for mods...");
        Mixins.addConfiguration("mixins.compatlib.mods.json");
    }

    @Override
    public List<String> getMixinConfigs() {
        List<String> mixins = new ArrayList<>();
        mixins.add("mixins.compatlib.mods.json");
        return mixins;
    }
}
