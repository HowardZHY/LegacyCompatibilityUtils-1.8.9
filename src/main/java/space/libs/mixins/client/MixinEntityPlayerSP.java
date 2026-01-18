package space.libs.mixins.client;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityPlayerSP.class, priority = 4800)
public class MixinEntityPlayerSP {

    @Inject(method = "canCommandSenderUseCommand", at = @At("HEAD"), cancellable = true)
    public void canCommandSenderUseCommand(int permLevel, String commandName, CallbackInfoReturnable<Boolean> cir) {
        if (Loader.isModLoaded("legacy_gm_switcher") && commandName.equals("gamemode")) {
            cir.setReturnValue(true);
        }
    }
}
