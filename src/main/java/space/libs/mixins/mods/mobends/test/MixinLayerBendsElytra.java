package space.libs.mixins.mods.mobends.test;

import net.minecraft.client.entity.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Pseudo
@Mixin(targets = "net.gobbob.mobends.client.renderer.entity.layers.LayerBendsElytra", remap = false)
public class MixinLayerBendsElytra {

    @Inject(method = "doRenderLayer", at = @At("HEAD"), cancellable = true)
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci) {
        ci.cancel();
    }

}
