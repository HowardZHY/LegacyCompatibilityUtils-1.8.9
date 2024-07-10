package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(S2EPacketCloseWindow.class)
public class MixinSPacketCloseWindow {

    @Shadow
    public void processPacket(INetHandlerPlayClient handler) {}

    public void func_180731_a(INetHandlerPlayClient handler) {
        this.processPacket(handler);
    }
}
