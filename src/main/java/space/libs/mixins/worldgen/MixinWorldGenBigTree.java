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
    void func_181631_a(BlockPos pos, float p_181631_2_, IBlockState block) {}

    public void func_180712_a(BlockPos pos, float p_180712_2_, Block block) {
        this.func_181631_a(pos, p_180712_2_, block.getDefaultState());
    }

}
