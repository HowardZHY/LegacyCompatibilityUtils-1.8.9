package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S47PacketPlayerListHeaderFooter.class)
public class MixinSPacketPlayerListHeaderFooter {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179699_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
