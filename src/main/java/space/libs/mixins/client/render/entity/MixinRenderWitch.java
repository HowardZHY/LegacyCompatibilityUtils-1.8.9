package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderWitch.class)
public abstract class MixinRenderWitch {

    @Shadow
    public void doRender(EntityWitch entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityWitch entity);

    public ResourceLocation func_180589_a(EntityWitch entity) {
        return this.getEntityTexture(entity);
    }

    public void func_180590_a(EntityWitch entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
