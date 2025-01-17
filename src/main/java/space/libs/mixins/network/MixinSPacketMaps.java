package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S34PacketMaps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S34PacketMaps.class)
public class MixinSPacketMaps {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180741_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
