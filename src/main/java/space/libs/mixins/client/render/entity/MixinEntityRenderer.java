package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.EntityRenderer;

import org.spongepowered.asm.mixin.*;

@SuppressWarnings("unused")
@Mixin(value = EntityRenderer.class, remap = false)
public abstract class MixinEntityRenderer {

    /** 1.7.10 Unused */
    public float field_78495_O = 0.0F;

    public float camRoll = 0.0F;

    public float field_78505_P = 0.0F;

    public float prevCamRoll = 0.0F;

    @Dynamic
    @Shadow(aliases = "updateCameraAndRender")
    public void func_181560_a(float partialTicks, long nanoTime) {}

    public void func_78480_b(float partialTicks) {
        this.func_181560_a(partialTicks, System.nanoTime());
    }

}
