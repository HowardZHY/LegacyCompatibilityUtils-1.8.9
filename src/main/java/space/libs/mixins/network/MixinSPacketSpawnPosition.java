package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S05PacketSpawnPosition.class)
public class MixinSPacketSpawnPosition {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180752_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
