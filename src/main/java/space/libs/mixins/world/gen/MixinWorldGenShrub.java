package space.libs.mixins.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenShrub;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(WorldGenShrub.class)
public class MixinWorldGenShrub extends MixinWorldGenTrees {

    @ShadowConstructor
    public void WorldGenShrub(IBlockState wood, IBlockState leaves) {}

    @NewConstructor
    public void WorldGenShrub(int wood, int leaves) {
        this.WorldGenShrub(Blocks.log.getStateFromMeta(wood), Blocks.leaves.getStateFromMeta(leaves));
    }
}
