package space.libs.mixins.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(WorldGenBigTree.class)
public abstract class MixinWorldGenBigTree extends MixinWorldGenAbstractTree {

    @Shadow
    void func_181631_a(BlockPos p_181631_1_, float p_181631_2_, IBlockState p_181631_3_) {}

    public void func_180712_a(BlockPos p_180712_1_, float p_180712_2_, Block p_180712_3_) {
        this.func_181631_a(p_180712_1_, p_180712_2_, p_180712_3_.getDefaultState());
    }

}
