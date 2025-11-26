package space.libs.mixins.mods.legacy.mobends;

import net.gobbob.mobends.util.BendsLogger;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.core.CompatLibDebug;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = BendsLogger.class, remap = false)
public class MixinBendsLogger {

    @Inject(method = "log", at = @At("HEAD"), cancellable = true)
    private static void log(String argText, BendsLogger argType, CallbackInfo ci) {
        if (argType == BendsLogger.DEBUG && CompatLibDebug.DEBUG) {
            LogManager.getLogger("MO'BENDS").info(argText);
        } else if (argType != BendsLogger.DEBUG) {
            LogManager.getLogger("MO'BENDS").info(argText);
        }
        ci.cancel();
    }
}
