package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S09PacketHeldItemChange.class)
public class MixinSPacketHeldItemChange {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180746_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
