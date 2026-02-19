package space.libs.mixins.block;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.block.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.ChangeSuperClass;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowSuperConstructor;

@SuppressWarnings("unused")
@Mixin(BlockCompressedPowered.class)
@ChangeSuperClass(BlockCompressed.class)
public class MixinBlockCompressedPowered extends MixinBlock {

    @ShadowSuperConstructor
    public void BlockCompressed(MapColor color) {}

    @NewConstructor
    public void BlockCompressedPowered(MapColor color) {
        this.BlockCompressed(color);
    }
}
