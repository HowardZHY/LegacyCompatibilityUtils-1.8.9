package space.libs.mixins.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenShrub;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(WorldGenShrub.class)
public class MixinWorldGenShrub extends MixinWorldGenTrees {

    @ShadowConstructor
    public void WorldGenShrub(IBlockState p_i46450_1_, IBlockState p_i46450_2_) {}

    @NewConstructor
    public void WorldGenShrub(int p_i2015_1_, int p_i2015_2_) {
        this.WorldGenShrub(Blocks.log.getStateFromMeta(p_i2015_1_), Blocks.leaves.getStateFromMeta(p_i2015_2_));
    }
}
