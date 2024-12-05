package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderZombie.class)
public abstract class MixinRenderZombie extends MixinRenderBiped<EntityZombie> {

    public MixinRenderZombie(RenderManager renderManager) {
        super(renderManager);
    }

    @Shadow
    public void doRender(EntityZombie entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    @Shadow
    protected ResourceLocation getEntityTexture(EntityZombie entity) {
        throw new AbstractMethodError();
    }

    /** doRender */
    public void func_180579_a(EntityZombie entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void func_76986_a(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.doRender((EntityZombie) entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void func_76986_a(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.doRender((EntityZombie) entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.doRender((EntityZombie) entity, x, y, z, p_76986_8_, partialTicks);
    }

    /** getEntityTexture */
    public ResourceLocation func_180578_a(EntityZombie entity) {
        return this.getEntityTexture(entity);
    }

}
