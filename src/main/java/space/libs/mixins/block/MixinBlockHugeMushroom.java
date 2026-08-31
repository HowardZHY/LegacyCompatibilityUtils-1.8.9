package space.libs.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(BlockHugeMushroom.class)
public class MixinBlockHugeMushroom extends MixinBlock {

    @ShadowConstructor
    public void BlockHugeMushroom(Material m, MapColor color, Block block) {}

    @NewConstructor
    public void BlockHugeMushroom(Material m, Block block) {
        this.BlockHugeMushroom(m, MapColor.sandColor, block);
    }
}
