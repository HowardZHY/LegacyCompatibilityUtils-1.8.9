package space.libs.mixins.mods.legacy.cs;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = "sushen.ioaea", remap = false)
public abstract class MixinCameraStudioPrivateFields {

    @Dynamic
    @ModifyConstant(method = "<clinit>", constant = @Constant(stringValue = "aa"), remap = false)
    private static String session(String old) {
        return "ae";
    }
}
