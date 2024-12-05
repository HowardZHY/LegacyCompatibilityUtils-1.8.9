package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderArrow.class)
public abstract class MixinRenderArrow {

    @Shadow
    public void doRender(EntityArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityArrow entity);

    public ResourceLocation func_180550_a(EntityArrow arrow) {
        return this.getEntityTexture(arrow);
    }

    public void func_180551_a(EntityArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
