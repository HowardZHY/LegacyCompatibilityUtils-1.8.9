package space.libs.mixins.worldgen;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.mixins.worldgen.MixinWorldGenHugeTrees;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(WorldGenMegaJungle.class)
public class MixinWorldGenMegaJungle extends MixinWorldGenHugeTrees {

    @NewConstructor
    public void WorldGenMegaJungle(boolean p_i45456_1_, int p_i45456_2_, int p_i45456_3_, int p_i45456_4_, int p_i45456_5_) {
        super.WorldGenHugeTrees(p_i45456_1_, p_i45456_2_, p_i45456_3_, p_i45456_4_, p_i45456_5_);
    }

    public void func_175932_b(World world, Random r, BlockPos pos, int p_175932_4_) {
        if (r.nextInt(3) > 0 && world.isAirBlock(pos)) {
            this.func_175905_a(world, pos, Blocks.vine, p_175932_4_);
        }
    }
}
