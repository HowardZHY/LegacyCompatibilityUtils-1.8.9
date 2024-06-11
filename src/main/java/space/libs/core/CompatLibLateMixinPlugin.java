package space.libs.core;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.ILateMixinLoader;
import zone.rong.mixinbooter.MixinLoader;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
@MixinLoader
public class CompatLibLateMixinPlugin implements ILateMixinLoader {

    public CompatLibLateMixinPlugin() {
        Mixins.addConfiguration("mixins.compatlib.mods.json");
        CompatLibCore.LOGGER.info("Loading Late Mixins for mods...");
    }

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.compatlib.mods.json");
    }

}
