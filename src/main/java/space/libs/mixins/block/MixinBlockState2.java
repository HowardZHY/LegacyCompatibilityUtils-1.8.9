package space.libs.mixins.block;

import net.minecraft.block.properties.IProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(targets = "net.minecraft.block.state.BlockState$2")
public abstract class MixinBlockState2 {
    @Shadow
    public abstract int compare(IProperty p_compare_1_, IProperty p_compare_2_);

    public int func_177548_a(IProperty p_compare_1_, IProperty p_compare_2_) {
        return this.compare(p_compare_1_, p_compare_2_);
    }
}
