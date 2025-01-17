package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S19PacketEntityHeadLook.class)
public class MixinSPacketEntityHeadLook {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180745_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
