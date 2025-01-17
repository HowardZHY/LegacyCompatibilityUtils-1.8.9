package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S0APacketUseBed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S0APacketUseBed.class)
public class MixinSPacketUseBed {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180744_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
