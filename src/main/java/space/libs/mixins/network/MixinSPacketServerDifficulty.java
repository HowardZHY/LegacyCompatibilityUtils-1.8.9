package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S41PacketServerDifficulty.class)
public class MixinSPacketServerDifficulty {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179829_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
