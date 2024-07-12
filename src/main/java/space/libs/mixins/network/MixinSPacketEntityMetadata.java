package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S1CPacketEntityMetadata.class)
public class MixinSPacketEntityMetadata {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180748_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
