package space.libs.mixins.block;

import com.google.common.cache.LoadingCache;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings({"UnstableApiUsage", "unused"})
@Mixin(BlockPattern.PatternHelper.class)
public abstract class MixinBlockPatternHelper {

    @ShadowConstructor
    public void PatternHelper(BlockPos posIn, EnumFacing fingerIn, EnumFacing thumbIn, LoadingCache<BlockPos, BlockWorldState> lcacheIn, int i, int j, int k) {}

    @NewConstructor
    public void PatternHelper(BlockPos posIn, EnumFacing fingerIn, EnumFacing thumbIn, LoadingCache<BlockPos, BlockWorldState> lcacheIn) {
        PatternHelper(posIn, fingerIn, thumbIn, lcacheIn, 0, 0, 0);
    }
}
