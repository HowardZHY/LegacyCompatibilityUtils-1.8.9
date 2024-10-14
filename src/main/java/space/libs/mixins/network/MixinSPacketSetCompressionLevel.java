package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(S46PacketSetCompressionLevel.class)
public class MixinSPacketSetCompressionLevel {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179802_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
