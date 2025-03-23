package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S44PacketWorldBorder.class)
public class MixinSPacketWorldBorder {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179787_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
