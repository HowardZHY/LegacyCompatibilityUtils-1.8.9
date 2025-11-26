package space.libs.mixins.mods.legacy.cs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Level;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = "sushen.oaiei", remap = false)
public abstract class MixinCameraStudioLogger {

    private static final Logger LOGGER = LogManager.getLogger("CameraStudio");

    @Dynamic
    @Inject(method = "eoiei(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V", at = @At("HEAD"), cancellable = true)
    private static void log(Level l, String msg, Throwable t, CallbackInfo ci) {
        if (l == Level.SEVERE) {
            LOGGER.error(msg, t);
        } else if (l == Level.WARNING) {
            LOGGER.warn(msg, t);
        } else {
            LOGGER.info(msg, t);
        }
        ci.cancel();
    }
}
