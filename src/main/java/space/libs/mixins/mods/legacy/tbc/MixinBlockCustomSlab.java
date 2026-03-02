package space.libs.mixins.mods.legacy.tbc;

import com.superdextor.thinkbigcore.blocks.BlockCustomSlab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.*;

@Mixin(BlockCustomSlab.class)
public abstract class MixinBlockCustomSlab extends BlockSlab {

    public MixinBlockCustomSlab(Material materialIn) {
        super(materialIn);
    }

    @ShadowSuperConstructor
    public void BlockSlab(Material materialIn) {}

    @ShadowConstructor
    public void BlockCustomSlab(Block block) {}

    @NewConstructor
    public void BlockCustomSlab(Material material) {
        BlockSlab(material);
        this.setLightOpacity(255);
        this.useNeighborBrightness = true;
    }

    @NewConstructor
    public void BlockCustomSlab(IBlockState state) {
        BlockCustomSlab(state.getBlock());
    }
}
