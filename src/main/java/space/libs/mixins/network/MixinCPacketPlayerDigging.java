package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C07PacketPlayerDigging.class)
public class MixinCPacketPlayerDigging {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180763_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
