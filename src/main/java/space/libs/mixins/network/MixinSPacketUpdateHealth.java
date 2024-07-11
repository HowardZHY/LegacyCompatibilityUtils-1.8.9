package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S06PacketUpdateHealth.class)
public class MixinSPacketUpdateHealth {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180750_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
