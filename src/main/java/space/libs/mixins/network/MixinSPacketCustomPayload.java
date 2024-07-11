package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S3FPacketCustomPayload.class)
public class MixinSPacketCustomPayload {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180734_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
