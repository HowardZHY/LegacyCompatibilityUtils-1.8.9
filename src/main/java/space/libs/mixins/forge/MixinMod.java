package space.libs.mixins.forge;

import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Mod.class, remap = false)
public interface MixinMod {
    String value();
}
