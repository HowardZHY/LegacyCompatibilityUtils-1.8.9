package space.libs.mixins;

import net.minecraft.block.properties.IProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(targets = "net.minecraft.block.state.BlockState$1")
public abstract class MixinBlockState1 {
    @Shadow
    public abstract String apply(IProperty p_apply_1_);

    public String func_177568_a(IProperty property) {
        return this.apply(property);
    }
}
