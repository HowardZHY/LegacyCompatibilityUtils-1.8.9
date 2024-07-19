package space.libs.mixins;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(WorldGenSwamp.class)
public abstract class MixinWorldGenSwamp extends MixinWorldGenerator {

    public void func_175922_a(World worldIn, BlockPos p_175922_2_, int p_175922_3_) {
        this.func_175905_a(worldIn, p_175922_2_, Blocks.vine, p_175922_3_);
        int var4 = 4;

        for (p_175922_2_ = p_175922_2_.down(); worldIn.getBlockState(p_175922_2_).getBlock().getMaterial() == Material.air && var4 > 0; --var4) {
            this.func_175905_a(worldIn, p_175922_2_, Blocks.vine, p_175922_3_);
            p_175922_2_ = p_175922_2_.down();
        }
    }

}
