package space.libs.mixins.mods.legacy.tbc;

import com.superdextor.thinkbigcore.blocks.BlockCustomOre;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@Mixin(BlockCustomOre.class)
public abstract class MixinBlockCustomOre extends BlockOre {

    @Shadow(remap = false)
    private MapColor theColor;

    @ShadowConstructor
    public void BlockCustomOre(Item drop, int min, int max, int xp) {}

    @NewConstructor
    public void BlockCustomOre(MapColor mapColorIn, int HarvestLevel) {
        BlockCustomOre(mapColorIn, null, 0, HarvestLevel, 0);
    }

    @NewConstructor
    public void BlockCustomOre(MapColor mapColorIn, Item dropItemIn, int amountIn, int HarvestLevel, int XPIn) {
        BlockCustomOre(dropItemIn, amountIn / 2, amountIn, HarvestLevel, XPIn, mapColorIn);
        this.setStepSound(soundTypeStone);
    }

    @NewConstructor
    public void BlockCustomOre(Item item, int min, int max, int lvl, int xp, MapColor color) {
        BlockCustomOre(item, min, max, xp);
        this.setStepSound(soundTypeStone);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("pickaxe", lvl);
        this.theColor = color;
    }

    @NewConstructor
    public void BlockCustomOre(Material material, MapColor mapColorIn, Item dropItemIn, int HarvestLevel) {
        BlockCustomOre(mapColorIn, dropItemIn, 1, HarvestLevel, 2);
        this.setHardness(3.3F);
        this.setResistance(18.0F);
    }

}
