package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C0APacketAnimation.class)
public class MixinCPacketAnimation {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_179721_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
