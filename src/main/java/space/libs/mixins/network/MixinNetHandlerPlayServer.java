package space.libs.mixins.network;

import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetHandlerPlayServer.class)
public abstract class MixinNetHandlerPlayServer implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

}
