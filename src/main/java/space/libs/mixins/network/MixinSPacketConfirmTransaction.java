package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S32PacketConfirmTransaction.class)
public class MixinSPacketConfirmTransaction {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180730_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
