package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C14PacketTabComplete;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C14PacketTabComplete.class)
public class MixinCPacketTabComplete {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180756_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
