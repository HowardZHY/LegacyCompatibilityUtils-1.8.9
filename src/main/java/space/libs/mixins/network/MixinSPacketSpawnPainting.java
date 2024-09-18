package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S10PacketSpawnPainting.class)
public class MixinSPacketSpawnPainting {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180722_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
