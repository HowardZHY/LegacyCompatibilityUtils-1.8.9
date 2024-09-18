package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S2APacketParticles;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(S2APacketParticles.class)
public class MixinSPacketParticles {

    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180740_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
