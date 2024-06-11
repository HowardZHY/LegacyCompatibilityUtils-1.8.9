package space.libs.core;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

@SuppressWarnings("all")
@MixinLoader
public class CompatLibLateMixinPlugin{

    public CompatLibLateMixinPlugin() {
        Mixins.addConfiguration("mixins.compatlib.mods.json");
        CompatLibCore.LOGGER.info("Loading Late Mixins for mods...");
    }

}
