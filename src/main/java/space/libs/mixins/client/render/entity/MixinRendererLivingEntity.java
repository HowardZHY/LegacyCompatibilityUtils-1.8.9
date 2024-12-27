package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.mods.MoBendsUtils;

@SuppressWarnings("unused")
@Mixin(RendererLivingEntity.class)
public abstract class MixinRendererLivingEntity<T extends EntityLivingBase> extends Render<T> {

    public MixinRendererLivingEntity(RenderManager renderManager) {
        super(renderManager);
    }

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

    @Inject(method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", at = @At("HEAD"), cancellable = true)
    private void RenderLivingEvent(T entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo callbackInfo) {
        if (MoBendsUtils.RenderLivingEvent((RendererLivingEntity<?>) (Object) this, entity, x, y, z, entityYaw, partialTicks)) {
            callbackInfo.cancel();
        }
    }
}
