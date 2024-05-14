package space.libs.mixins.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(RenderManager.class)
public abstract class MixinRenderManager {

    @Shadow
    public abstract Render getEntityRenderObject(Entity entityIn);

    @Shadow
    public abstract boolean renderEntityStatic(Entity entity, float partialTicks, boolean hideDebugBox);

    @Shadow
    public abstract void renderWitherSkull(Entity entityIn, float partialTicks);

    @Shadow
    public abstract boolean doRenderEntity(Entity entity, double x, double y, double z, float entityYaw, float partialTicks, boolean hideDebugBox);

    /** renderEntityStatic */
    public void func_188388_a(Entity entity, float partialTicks, boolean hideDebugBox) {
        this.renderEntityStatic(entity, partialTicks, hideDebugBox);
    }

    /** renderMultipass */
    public void func_188389_a(Entity entity, float partialTicks) {
        this.renderWitherSkull(entity, partialTicks);
    }

    /** isRenderMultipass */
    public boolean func_188390_b(Entity entity) {
        //return this.getEntityRenderObject(entity).func_188295_H_();
        return false;
    }

    /** doRenderEntity */
    public void func_188391_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks, boolean hideDebugBox) {
        this.doRenderEntity(entity, x, y, z, entityYaw, partialTicks, hideDebugBox);
    }

}
