package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(
    targets = {
        "net.minecraft.tileentity.TileEntityBeacon",
        "net.minecraft.tileentity.TileEntityBrewingStand",
        "net.minecraft.tileentity.TileEntityChest",
        "net.minecraft.tileentity.TileEntityDaylightDetector",
        "net.minecraft.tileentity.TileEntityEnchantmentTable",
        "net.minecraft.tileentity.TileEntityEnderChest",
        "net.minecraft.tileentity.TileEntityFurnace",
        "net.minecraft.tileentity.TileEntityMobSpawner",
        "net.minecraft.tileentity.TileEntityPiston"
    }
)
public abstract class MixinTileEntities implements IUpdatePlayerListBox {

    @Shadow
    @Override
    public abstract void update();

}
