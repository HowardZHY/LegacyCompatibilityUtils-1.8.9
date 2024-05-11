package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.EntityRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Shadow
    public void updateCameraAndRender(float partialTicks, long nanoTime) {}

    public void func_78480_b(float partialTicks) {
        this.updateCameraAndRender(partialTicks, System.nanoTime());
    }

}
