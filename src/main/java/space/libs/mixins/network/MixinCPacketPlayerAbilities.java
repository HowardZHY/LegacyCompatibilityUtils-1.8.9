package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(C13PacketPlayerAbilities.class)
public class MixinCPacketPlayerAbilities {

    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180761_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
