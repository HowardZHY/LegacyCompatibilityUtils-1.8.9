package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S35PacketUpdateTileEntity.class)
public class MixinSPacketUpdateTileEntity {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180725_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
