package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S20PacketEntityProperties.class)
public class MixinSPacketEntityProperties {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180754_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
