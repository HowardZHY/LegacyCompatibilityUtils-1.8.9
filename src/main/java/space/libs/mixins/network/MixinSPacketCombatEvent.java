package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(S42PacketCombatEvent.class)
public class MixinSPacketCombatEvent {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_179771_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
