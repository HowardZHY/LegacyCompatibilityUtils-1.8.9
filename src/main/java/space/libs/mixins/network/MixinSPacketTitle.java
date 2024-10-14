package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S45PacketTitle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(S45PacketTitle.class)
public class MixinSPacketTitle {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179759_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
