package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityDaylightDetector.class)
public class MixinTileEntityDaylightDetector implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
