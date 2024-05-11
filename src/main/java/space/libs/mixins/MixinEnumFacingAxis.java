package space.libs.mixins;

import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(targets = "net.minecraft.util.EnumFacing$Axis")
public abstract class MixinEnumFacingAxis {
    @Shadow
    public abstract boolean apply(EnumFacing p_apply_1_);

    public boolean func_176718_a(EnumFacing facing) {
        return this.apply(facing);
    }
}
