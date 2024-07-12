package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C0BPacketEntityAction.class)
public class MixinCPacketEntityAction {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180765_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
