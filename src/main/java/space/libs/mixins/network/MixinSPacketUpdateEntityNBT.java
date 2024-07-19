package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S49PacketUpdateEntityNBT.class)
public class MixinSPacketUpdateEntityNBT {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179762_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
