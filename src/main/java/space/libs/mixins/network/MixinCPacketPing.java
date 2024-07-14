package space.libs.mixins.network;

import net.minecraft.network.status.INetHandlerStatusServer;
import net.minecraft.network.status.client.C01PacketPing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C01PacketPing.class)
public class MixinCPacketPing {

    @Shadow
    public void processPacket(INetHandlerStatusServer handler) {}

    public void func_180774_a(INetHandlerStatusServer handler) {
        this.processPacket(handler);
    }
}
