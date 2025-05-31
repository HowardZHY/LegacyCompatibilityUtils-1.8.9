package space.libs.mixins.client.render;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.interfaces.IWorldRenderer;

@Mixin(EntityFX.class)
public class MixinEntityFX {

    @Inject(method = "renderParticle", at = @At("HEAD"))
    public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ, CallbackInfo ci) {
        IWorldRenderer accessor = IWorldRenderer.get(worldRendererIn);
        if (accessor.getVertexFormatElement() == null) {
            if (worldRendererIn.getVertexFormat() == null) {
                accessor.func_178967_a(DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
            }
            accessor.setVertexFormatElement(worldRendererIn.getVertexFormat().getElement(accessor.getVertexFormatIndex()));
        }
    }
}
