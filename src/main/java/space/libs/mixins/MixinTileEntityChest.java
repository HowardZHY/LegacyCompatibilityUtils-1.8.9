package space.libs.mixins;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityChest.class)
public class MixinTileEntityChest implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
