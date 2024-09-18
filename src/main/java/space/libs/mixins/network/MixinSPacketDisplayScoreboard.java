package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S3DPacketDisplayScoreboard.class)
public class MixinSPacketDisplayScoreboard {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180747_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
