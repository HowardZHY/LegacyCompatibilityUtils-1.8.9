package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S23PacketBlockChange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S23PacketBlockChange.class)
public class MixinSPacketBlockChange {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180727_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
