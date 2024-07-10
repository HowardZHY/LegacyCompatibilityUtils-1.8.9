package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C18PacketSpectate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C18PacketSpectate.class)
public class MixinCPacketSpectate {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_179728_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
