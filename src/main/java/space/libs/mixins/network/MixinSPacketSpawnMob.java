package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S0FPacketSpawnMob.class)
public class MixinSPacketSpawnMob {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180721_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
