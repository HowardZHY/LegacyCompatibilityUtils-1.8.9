package space.libs.mixins.network;

import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(C0DPacketCloseWindow.class)
public class MixinCPacketCloseWindow {

    @Shadow
    public void processPacket(INetHandlerPlayServer handler) {}

    public void func_180759_a(INetHandlerPlayServer handler) {
        this.processPacket(handler);
    }
}
