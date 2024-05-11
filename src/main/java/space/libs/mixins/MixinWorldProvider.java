package space.libs.mixins;

import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(WorldProvider.class)
public abstract class MixinWorldProvider {

    @Shadow
    public boolean getHasNoSky() {
        throw new AbstractMethodError();
    }

    /** hasSkyLight */
    public boolean func_191066_m() {
        return !this.getHasNoSky();
    }

}
