package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderEntityItem.class)
public abstract class MixinRenderEntityItem {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityItem entity);

    @Shadow
    public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    public void func_177075_a(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public ResourceLocation func_177076_a(EntityItem entity) {
        return this.getEntityTexture(entity);
    }

}
