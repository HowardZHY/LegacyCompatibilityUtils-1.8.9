package space.libs.mixins.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.mixins.worldgen.MixinWorldGenAbstractTree;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(WorldGenHugeTrees.class)
public class MixinWorldGenHugeTrees extends MixinWorldGenAbstractTree {

    @ShadowConstructor
    public void WorldGenHugeTrees(boolean p_i46447_1_, int p_i46447_2_, int p_i46447_3_, IBlockState p_i46447_4_, IBlockState p_i46447_5_) {}

    @NewConstructor
    public void WorldGenHugeTrees(boolean p_i45458_1_, int p_i45458_2_, int p_i45458_3_, int p_i45458_4_, int p_i45458_5_) {
        this.WorldGenHugeTrees(p_i45458_1_, p_i45458_2_, p_i45458_3_, Blocks.log.getStateFromMeta(p_i45458_4_), Blocks.leaves.getStateFromMeta(p_i45458_5_));
    }
}
