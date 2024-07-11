package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityBrewingStand.class)
public class MixinTileEntityBrewingStand implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
