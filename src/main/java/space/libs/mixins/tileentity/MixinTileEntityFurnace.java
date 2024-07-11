package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityFurnace.class)
public class MixinTileEntityFurnace implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
