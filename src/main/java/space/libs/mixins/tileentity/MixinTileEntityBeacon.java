package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityBeacon.class)
public abstract class MixinTileEntityBeacon implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

}
