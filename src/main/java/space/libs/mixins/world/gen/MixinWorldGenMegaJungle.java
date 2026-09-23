package space.libs.mixins.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(WorldGenMegaJungle.class)
public class MixinWorldGenMegaJungle extends MixinWorldGenHugeTrees {

    @ShadowConstructor
    public void WorldGenMegaJungle(boolean notify, int height1, int height2, IBlockState wood, IBlockState leaves) {}

    @NewConstructor
    public void WorldGenMegaJungle(boolean notify, int height1, int height2, int wood, int leaves) {
        WorldGenMegaJungle(notify, height1, height2, Blocks.log.getStateFromMeta(wood), Blocks.leaves.getStateFromMeta(leaves));
    }

    public void func_175932_b(World world, Random r, BlockPos pos, int meta) {
        if (r.nextInt(3) > 0 && world.isAirBlock(pos)) {
            this.func_175905_a(world, pos, Blocks.vine, meta);
        }
    }
}
