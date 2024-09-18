package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(C08PacketPlayerBlockPlacement.class)
public class MixinCPacketPlayerBlockPlacement {

    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180769_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
