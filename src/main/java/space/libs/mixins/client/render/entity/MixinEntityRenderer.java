package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.EntityRenderer;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(value = EntityRenderer.class, remap = false)
public abstract class MixinEntityRenderer {

    private boolean Activated = false;

    private long NanoTimeCache;

    /** 1.7.10 Unused */
    public float field_78495_O = 0.0F;

    public float camRoll = 0.0F;

    public float field_78505_P = 0.0F;

    public float prevCamRoll = 0.0F;

    @Dynamic
    @Shadow(aliases = "updateCameraAndRender")
    public void func_181560_a(float partialTicks, long nanoTime) {}

    public void func_78480_b(float partialTicks) {
        if (this.Activated) {
            this.func_181560_a(partialTicks, NanoTimeCache);
        } else {
            this.func_181560_a(partialTicks, System.nanoTime());
        }
    }

    @Inject(method = "updateCameraAndRender", at = @At("HEAD"), cancellable = true)
    public void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo ci) {
        if (!Activated && !this.getClass().getName().startsWith("net.minecraft")) {
            this.Activated = true;
            this.NanoTimeCache = nanoTime;
            this.func_78480_b(partialTicks);
            ci.cancel();
        }
    }

    @Inject(method = "updateCameraAndRender", at = @At("RETURN"))
    public void updateCameraAndRender2(float partialTicks, long nanoTime, CallbackInfo ci) {
        this.Activated = false;
        this.NanoTimeCache = 0L;
    }
}
