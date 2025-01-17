package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S2CPacketSpawnGlobalEntity.class)
public class MixinSPacketSpawnGlobalEntity {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180720_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
