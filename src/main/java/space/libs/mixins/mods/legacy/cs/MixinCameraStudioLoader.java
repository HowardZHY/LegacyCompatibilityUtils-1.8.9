package space.libs.mixins.mods.legacy.cs;

import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = "sushen.ieaea", remap = false)
public abstract class MixinCameraStudioLoader {

    @Shadow
    private static String ioaeo;

    @Dynamic
    @ModifyConstant(method = "aoioe(F)V", constant = @Constant(stringValue = "1.8"), remap = false)
    private static String version(String old) {
        LogManager.getLogger("CameraStudio").info("Detected : " + ioaeo);
        return ioaeo;
    }
}
