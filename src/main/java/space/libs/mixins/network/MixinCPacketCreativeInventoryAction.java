package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(C10PacketCreativeInventoryAction.class)
public class MixinCPacketCreativeInventoryAction {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180767_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
