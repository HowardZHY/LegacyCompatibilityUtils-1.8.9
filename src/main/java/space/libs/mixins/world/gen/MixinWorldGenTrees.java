package space.libs.mixins.world.gen;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(WorldGenTrees.class)
public class MixinWorldGenTrees extends MixinWorldGenAbstractTree {

    @ShadowConstructor
    public void WorldGenTrees(boolean notify, int height, IBlockState wood, IBlockState leaves, boolean vines) {}

    @NewConstructor
    public void WorldGenTrees(boolean notify, int height, int wood, int leaves, boolean vines) {
        this.WorldGenTrees(notify, height, Blocks.log.getStateFromMeta(wood), Blocks.leaves.getStateFromMeta(leaves), vines);
    }

    public void func_175923_a(World worldIn, BlockPos pos, int meta) {
        this.func_175905_a(worldIn, pos, Blocks.vine, meta);
        int var4 = 4;
        for (pos = pos.down(); worldIn.getBlockState(pos).getBlock().getMaterial() == Material.air && var4 > 0; --var4) {
            this.func_175905_a(worldIn, pos, Blocks.vine, meta);
            pos = pos.down();
        }
    }
}
