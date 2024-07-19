package space.libs.mixins;

import net.minecraft.block.Block;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(value = StatList.class, remap = false)
public class MixinStatList {

    @Shadow(aliases = "func_75924_a")
    private static void replaceAllSimilarBlocks(StatBase[] p_75924_0_, boolean useItemIds) {}

    @Shadow(aliases = "func_151180_a")
    private static void mergeStatBases(StatBase[] statBaseIn, Block p_151180_1_, Block p_151180_2_, boolean useItemIds) {}

    @Public
    private static void func_75924_a(StatBase[] p_75924_0_) {
        replaceAllSimilarBlocks(p_75924_0_, true);
    }

    @Public
    private static void func_151180_a(StatBase[] statBaseIn, Block p_151180_1_, Block p_151180_2_) {
        mergeStatBases(statBaseIn, p_151180_1_, p_151180_2_, true);
    }
}
