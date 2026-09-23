package space.libs.mixins.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(WorldGenHugeTrees.class)
public class MixinWorldGenHugeTrees extends MixinWorldGenAbstractTree {

    @ShadowConstructor
    public void WorldGenHugeTrees(boolean notify, int height1, int height2, IBlockState wood, IBlockState leaves) {}

    @NewConstructor
    public void WorldGenHugeTrees(boolean notify, int height1, int height2, int wood, int leaves) {
        this.WorldGenHugeTrees(notify, height1, height2, Blocks.log.getStateFromMeta(wood), Blocks.leaves.getStateFromMeta(leaves));
    }
}
