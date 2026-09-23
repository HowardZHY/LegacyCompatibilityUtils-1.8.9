package space.libs.mixins.client.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.client.model.animation.ModelBlockAnimation;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Animation.class, remap = false, priority = 100)
public abstract class MixinAnimation {

    @Shadow
    private @Final ModelBlockAnimation defaultModelBlockAnimation;

    @Inject(method = "loadVanillaAnimation", at = @At("HEAD"), cancellable = true)
    public void loadVanillaAnimation(ResourceLocation armatureLocation, CallbackInfoReturnable<ModelBlockAnimation> cir) {
        if (armatureLocation == null) {
            cir.setReturnValue(defaultModelBlockAnimation);
        }
    }
}
