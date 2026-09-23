package space.libs.mixins.block;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraft.block.state.pattern.BlockPattern$CacheLoader")
public abstract class MixinBlockPatternCacheLoader {

    @ShadowConstructor
    public void CacheLoader(World worldIn, boolean p_i46460_2_) {}

    @NewConstructor
    public void CacheLoader(World world) {
        CacheLoader(world, false);
    }
}
