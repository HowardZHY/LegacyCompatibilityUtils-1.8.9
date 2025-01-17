package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S48PacketResourcePackSend.class)
public class MixinSPacketResourcePackSend {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179782_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
