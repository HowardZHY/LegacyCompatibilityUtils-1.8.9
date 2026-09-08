package space.libs.mixins.worldgen;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(WorldGenSwamp.class)
public abstract class MixinWorldGenSwamp extends MixinWorldGenerator {

    public void func_175922_a(World worldIn, BlockPos pos, int meta) {
        this.func_175905_a(worldIn, pos, Blocks.vine, meta);
        int var4 = 4;
        for (pos = pos.down(); worldIn.getBlockState(pos).getBlock().getMaterial() == Material.air && var4 > 0; --var4) {
            this.func_175905_a(worldIn, pos, Blocks.vine, meta);
            pos = pos.down();
        }
    }

}
