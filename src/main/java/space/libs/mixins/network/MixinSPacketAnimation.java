package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S0BPacketAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S0BPacketAnimation.class)
public class MixinSPacketAnimation {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180723_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
