package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S30PacketWindowItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S30PacketWindowItems.class)
public class MixinSPacketWindowItems {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180732_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}

