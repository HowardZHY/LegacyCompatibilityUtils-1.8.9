package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityEnderChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityEnderChest.class)
public abstract class MixinTileEntityEnderChest implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

}
