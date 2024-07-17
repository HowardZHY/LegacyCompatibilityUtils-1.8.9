package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderGuardian;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderGuardian.class)
public abstract class MixinRenderGuardian {

    @Shadow
    public void doRender(EntityGuardian entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityGuardian entity);

    @Shadow
    protected void preRenderCallback(EntityGuardian entitylivingbaseIn, float partialTickTime) {}

    @Shadow
    public abstract boolean shouldRender(EntityGuardian livingEntity, ICamera camera, double camX, double camY, double camZ);

    public boolean func_177104_a(EntityLiving livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return this.shouldRender((EntityGuardian) livingEntity, camera, camX, camY, camZ);
    }

    public void func_177109_a(EntityGuardian entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public ResourceLocation func_177111_a(EntityGuardian entity) {
        return this.getEntityTexture(entity);
    }

    public void func_177112_a(EntityGuardian entitylivingbaseIn, float partialTickTime) {
        this.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    public boolean func_177113_a(EntityGuardian livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return this.shouldRender(livingEntity, camera, camX, camY, camZ);
    }
}
