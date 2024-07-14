package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C01PacketChatMessage.class)
public class MixinCPacketChatMessage {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180757_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
