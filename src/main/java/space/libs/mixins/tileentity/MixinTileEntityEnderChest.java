package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityEnderChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityEnderChest.class)
public class MixinTileEntityEnderChest implements IUpdatePlayerListBox {

    @Shadow
    @Override
    public void update() {}

}
