package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C16PacketClientStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C16PacketClientStatus.class)
public class MixinCPacketClientStatus {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180758_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
