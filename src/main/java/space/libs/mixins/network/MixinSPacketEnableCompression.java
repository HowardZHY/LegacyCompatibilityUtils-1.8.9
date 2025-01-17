package space.libs.mixins.network;

import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S03PacketEnableCompression.class)
public class MixinSPacketEnableCompression {

    @Shadow
    public void processPacket(INetHandlerLoginClient handler) {}

    public void func_179732_a(INetHandlerLoginClient handler) {
        this.processPacket(handler);
    }
}
