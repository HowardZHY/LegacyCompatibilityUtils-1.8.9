package space.libs.mixins.server;

import net.minecraft.server.dedicated.DedicatedPlayerList;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DedicatedServer.class)
public abstract class MixinDedicatedServer {

    @Shadow
    public abstract DedicatedPlayerList getConfigurationManager();

    public DedicatedPlayerList func_180508_aN() {
        return this.getConfigurationManager();
    }
}
