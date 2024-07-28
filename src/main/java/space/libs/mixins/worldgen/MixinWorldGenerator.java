package space.libs.mixins.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(WorldGenerator.class)
public abstract class MixinWorldGenerator {

    @Shadow
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state) {}

    public void func_175905_a(World worldIn, BlockPos p_175905_2_, Block p_175905_3_, int p_175905_4_) {
        this.setBlockAndNotifyAdequately(worldIn, p_175905_2_, p_175905_3_.getStateFromMeta(p_175905_4_));
    }

    public void func_175906_a(World worldIn, BlockPos p_175906_2_, Block p_175906_3_) {
        this.func_175905_a(worldIn, p_175906_2_, p_175906_3_, 0);
    }

}
