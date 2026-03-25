package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S39PacketPlayerAbilities.class)
public class MixinSPacketPlayerAbilities {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180742_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
