package space.libs.mixins.network;

import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.server.S00PacketDisconnect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S00PacketDisconnect.class)
public class MixinSPacketDisconnect {

    @Shadow
    public void processPacket(INetHandlerLoginClient handler) {}

    public void func_180772_a(INetHandlerLoginClient handler) {
        this.processPacket(handler);
    }
}
