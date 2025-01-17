package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S26PacketMapChunkBulk.class)
public class MixinSPacketMapChunkBulk {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180738_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
