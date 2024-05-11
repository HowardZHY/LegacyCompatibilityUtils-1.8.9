package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderBoat.class)
public abstract class MixinRenderBoat {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityBoat entity);

    @Shadow
    public void doRender(EntityBoat entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    public void func_180552_a(EntityBoat entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation func_180553_a(EntityBoat entity) {
        return this.getEntityTexture(entity);
    }

}
