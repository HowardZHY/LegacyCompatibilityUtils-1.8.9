package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S28PacketEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S28PacketEffect.class)
public class MixinSPacketEffect {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180739_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
