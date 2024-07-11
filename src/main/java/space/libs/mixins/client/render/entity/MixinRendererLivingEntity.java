package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RendererLivingEntity.class)
public abstract class MixinRendererLivingEntity {

    @Shadow
    public void renderName(EntityLivingBase entity, double x, double y, double z) {}

    @Shadow
    protected abstract boolean canRenderName(EntityLivingBase entity);

    public void func_77033_b(EntityLivingBase entity, double x, double y, double z) {
        this.renderName(entity, x, y, z);
    }

    public boolean func_110813_b(EntityLivingBase targetEntity) {
        return this.canRenderName(targetEntity);
    }
}
