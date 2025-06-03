package space.libs.mixins.worldgen;

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
    public void WorldGenMegaJungle(boolean p_i46447_1_, int p_i46447_2_, int p_i46447_3_, IBlockState p_i46447_4_, IBlockState p_i46447_5_) {}

    @NewConstructor
    public void WorldGenMegaJungle(boolean p_i45456_1_, int p_i45456_2_, int p_i45456_3_, int p_i45456_4_, int p_i45456_5_) {
        WorldGenMegaJungle(p_i45456_1_, p_i45456_2_, p_i45456_3_, Blocks.log.getStateFromMeta(p_i45456_4_), Blocks.leaves.getStateFromMeta(p_i45456_5_));
    }

    public void func_175932_b(World world, Random r, BlockPos pos, int p_175932_4_) {
        if (r.nextInt(3) > 0 && world.isAirBlock(pos)) {
            this.func_175905_a(world, pos, Blocks.vine, p_175932_4_);
        }
    }
}
