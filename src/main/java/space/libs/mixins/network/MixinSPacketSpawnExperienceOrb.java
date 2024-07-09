package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S11PacketSpawnExperienceOrb.class)
public class MixinSPacketSpawnExperienceOrb {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180719_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
