package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S31PacketWindowProperty.class)
public class MixinSPacketWindowProperty {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180733_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
