package space.libs.mixins.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(BlockHelper.class)
public abstract class MixinBlockHelper {

    @Shadow
    public abstract boolean apply(IBlockState p_apply_1_);

    public boolean func_177643_a(IBlockState state) {
        return this.apply(state);
    }
}
