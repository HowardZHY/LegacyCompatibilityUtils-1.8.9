package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S38PacketPlayerListItem.class)
public class MixinSPacketPlayerListItem {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180743_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
