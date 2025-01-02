package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.EntityRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    /** 1.7.10 Unused */
    public float field_78495_O = 0.0F;

    public float camRoll = 0.0F;

    public float field_78505_P = 0.0F;

    public float prevCamRoll = 0.0F;

    @Shadow
    public void updateCameraAndRender(float partialTicks, long nanoTime) {}

    public void func_78480_b(float partialTicks) {
        this.updateCameraAndRender(partialTicks, System.nanoTime());
    }

}
