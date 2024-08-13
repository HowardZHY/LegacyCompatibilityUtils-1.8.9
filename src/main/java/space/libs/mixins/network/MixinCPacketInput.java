package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C0CPacketInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C0CPacketInput.class)
public class MixinCPacketInput {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180766_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
