package space.libs.mixins.network;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.server.network.NetHandlerLoginServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetHandlerLoginServer.class)
public abstract class MixinNetHandlerLoginServer implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

}
