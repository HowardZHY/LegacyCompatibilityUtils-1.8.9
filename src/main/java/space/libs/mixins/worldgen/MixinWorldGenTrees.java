package space.libs.mixins.worldgen;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.mixins.worldgen.MixinWorldGenAbstractTree;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(WorldGenTrees.class)
public class MixinWorldGenTrees extends MixinWorldGenAbstractTree {

    @ShadowConstructor
    public void WorldGenTrees(boolean p_i46446_1_, int p_i46446_2_, IBlockState p_i46446_3_, IBlockState p_i46446_4_, boolean p_i46446_5_) {}

    @NewConstructor
    public void WorldGenTrees(boolean p_i2028_1_, int p_i2028_2_, int p_i2028_3_, int p_i2028_4_, boolean p_i2028_5_) {
        this.WorldGenTrees(p_i2028_1_, p_i2028_2_, Blocks.log.getStateFromMeta(p_i2028_3_), Blocks.leaves.getStateFromMeta(p_i2028_4_), p_i2028_5_);
    }

    public void func_175923_a(World worldIn, BlockPos pos, int p_175923_3_) {
        this.func_175905_a(worldIn, pos, Blocks.vine, p_175923_3_);
        int var4 = 4;

        for (pos = pos.down(); worldIn.getBlockState(pos).getBlock().getMaterial() == Material.air && var4 > 0; --var4) {
            this.func_175905_a(worldIn, pos, Blocks.vine, p_175923_3_);
            pos = pos.down();
        }
    }

}
