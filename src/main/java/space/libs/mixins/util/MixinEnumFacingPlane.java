package space.libs.mixins.util;

import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = EnumFacing.Plane.class, remap = false)
public abstract class MixinEnumFacingPlane {

    @Shadow
    public abstract boolean apply(EnumFacing p_apply_1_);

    public boolean func_179519_a(EnumFacing facing) {
        return this.apply(facing);
    }
}
