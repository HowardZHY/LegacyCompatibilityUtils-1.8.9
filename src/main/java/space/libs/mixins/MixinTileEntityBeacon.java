package space.libs.mixins;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityBeacon.class)
public class MixinTileEntityBeacon implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
