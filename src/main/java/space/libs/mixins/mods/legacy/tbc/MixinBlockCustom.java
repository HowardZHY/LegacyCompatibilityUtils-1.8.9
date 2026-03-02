package space.libs.mixins.mods.legacy.tbc;

import com.superdextor.thinkbigcore.blocks.BlockCustom;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@Mixin(value = BlockCustom.class, remap = false)
public class MixinBlockCustom extends Block {

    public MixinBlockCustom(Material materialIn) {
        super(materialIn);
    }

    @Shadow
    private MapColor theColor;

    @Shadow
    private boolean beaconBlock;

    @ShadowConstructor
    public void BlockCustom(Material materialIn) {}

    @NewConstructor
    public void BlockCustom(Material materialIn, MapColor mapColorIn, boolean isBeaconBase) {
        BlockCustom(materialIn);
        this.theColor = mapColorIn;
        this.beaconBlock = isBeaconBase;
    }
}
