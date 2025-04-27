package space.libs.mixins.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(BlockStateHelper.class)
public abstract class MixinBlockStateHelper {

    @Shadow
    public abstract boolean apply(IBlockState p_apply_1_);

    public boolean func_177639_a(IBlockState state) {
        return this.apply(state);
    }
}
