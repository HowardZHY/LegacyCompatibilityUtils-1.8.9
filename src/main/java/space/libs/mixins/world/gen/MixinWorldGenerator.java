package space.libs.mixins.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = WorldGenerator.class, priority = 100)
public abstract class MixinWorldGenerator {

    @Shadow
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state) {}

    public void func_175905_a(World worldIn, BlockPos pos, Block block, int meta) {
        this.setBlockAndNotifyAdequately(worldIn, pos, block.getStateFromMeta(meta));
    }

    public void func_175906_a(World worldIn, BlockPos pos, Block block) {
        this.func_175905_a(worldIn, pos, block, 0);
    }

}
