package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderArrow.class)
public class MixinRenderArrow {

    @Final
    @Shadow
    private static ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");

    @Shadow
    public void doRender(EntityArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    public ResourceLocation func_180550_a(EntityArrow var1) {
        return arrowTextures;
    }

    public void func_180551_a(EntityArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
