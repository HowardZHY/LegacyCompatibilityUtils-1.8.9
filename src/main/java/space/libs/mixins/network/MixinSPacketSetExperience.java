package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S1FPacketSetExperience.class)
public class MixinSPacketSetExperience {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180749_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
