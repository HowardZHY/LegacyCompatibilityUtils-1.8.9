package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityPiston;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityPiston.class)
public class MixinTileEntityPiston implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
