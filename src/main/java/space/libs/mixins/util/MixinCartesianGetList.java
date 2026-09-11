package space.libs.mixins.util;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(targets = "net.minecraft.util.Cartesian$GetList")
public abstract class MixinCartesianGetList<T> {

    @Shadow
    public abstract List<T> apply(Object[] p_apply_1_);

    public List<T> func_179324_a(Object[] p_apply_1_) {
        return this.apply(p_apply_1_);
    }
}
