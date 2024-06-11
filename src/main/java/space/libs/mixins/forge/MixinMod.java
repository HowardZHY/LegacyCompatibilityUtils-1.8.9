package space.libs.mixins.forge;

import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Mod.class)
public interface MixinMod {
    String value();
}
