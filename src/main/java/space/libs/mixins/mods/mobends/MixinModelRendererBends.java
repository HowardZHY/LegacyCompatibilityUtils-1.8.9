package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.client.model.ModelRendererBends;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(value = ModelRendererBends.class, remap = false)
public abstract class MixinModelRendererBends {

    @Dynamic
    @Shadow
    public boolean field_78812_q;

    @Inject(method = "func_78790_a", at = @At("TAIL"))
    public void addBox(float p_78790_1_, float p_78790_2_, float p_78790_3_, int p_78790_4_, int p_78790_5_, int p_78790_6_, float p_78790_7_, CallbackInfo ci) {
        this.field_78812_q = false;
    }
}
