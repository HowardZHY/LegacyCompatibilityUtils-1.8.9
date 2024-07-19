package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S43PacketCamera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S43PacketCamera.class)
public class MixinSPacketCamera {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179779_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
