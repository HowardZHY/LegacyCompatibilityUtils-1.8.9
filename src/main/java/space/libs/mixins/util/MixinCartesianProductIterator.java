package space.libs.mixins.util;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net.minecraft.util.Cartesian$Product$ProductIterator")
public abstract class MixinCartesianProductIterator<T> {

    @Shadow
    public abstract T[] next();

    public T[] func_179421_a() {
        return next();
    }
}
