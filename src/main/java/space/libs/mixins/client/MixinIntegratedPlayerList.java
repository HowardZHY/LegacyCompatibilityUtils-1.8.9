package space.libs.mixins.client;

import net.minecraft.server.integrated.IntegratedPlayerList;
import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(IntegratedPlayerList.class)
public abstract class MixinIntegratedPlayerList {

    @Shadow
    public abstract IntegratedServer getServerInstance();

    public IntegratedServer func_180603_b() {
        return this.getServerInstance();
    }

}
