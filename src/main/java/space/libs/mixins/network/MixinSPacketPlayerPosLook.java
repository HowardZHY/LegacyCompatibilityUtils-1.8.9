package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S08PacketPlayerPosLook.class)
public class MixinSPacketPlayerPosLook {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180718_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
