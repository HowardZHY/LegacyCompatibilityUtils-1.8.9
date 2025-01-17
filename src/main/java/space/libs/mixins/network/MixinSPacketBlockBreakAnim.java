package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S25PacketBlockBreakAnim.class)
public class MixinSPacketBlockBreakAnim {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180724_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
