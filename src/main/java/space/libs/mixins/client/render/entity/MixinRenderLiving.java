package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderLiving.class)
public abstract class MixinRenderLiving {

    @Shadow
    protected abstract boolean canRenderName(EntityLiving entity);

    @Shadow
    public abstract boolean shouldRender(EntityLiving livingEntity, ICamera camera, double camX, double camY, double camZ);

    public boolean func_110813_b(EntityLiving entity) {
        return this.canRenderName(entity);
    }

    public boolean func_177104_a(EntityLiving livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return this.shouldRender(livingEntity, camera, camX, camY, camZ);
    }
}
