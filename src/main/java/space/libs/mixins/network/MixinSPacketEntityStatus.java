package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S19PacketEntityStatus.class)
public class MixinSPacketEntityStatus {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180736_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
