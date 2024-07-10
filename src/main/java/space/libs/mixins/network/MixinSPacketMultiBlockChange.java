package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S22PacketMultiBlockChange.class)
public class MixinSPacketMultiBlockChange {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180729_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
