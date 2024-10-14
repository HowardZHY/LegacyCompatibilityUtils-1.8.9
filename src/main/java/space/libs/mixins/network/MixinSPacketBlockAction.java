package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S24PacketBlockAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(S24PacketBlockAction.class)
public class MixinSPacketBlockAction {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180726_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
