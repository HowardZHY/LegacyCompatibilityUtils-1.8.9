package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C19PacketResourcePackStatus.class)
public class MixinCPacketResourcePackStatus {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_179718_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }

}
