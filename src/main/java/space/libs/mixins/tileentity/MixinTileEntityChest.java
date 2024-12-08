package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityChest.class)
public abstract class MixinTileEntityChest implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

}
