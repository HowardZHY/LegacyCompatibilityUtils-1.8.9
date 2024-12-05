package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.entity.EntityLeashKnot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderLeashKnot.class)
public abstract class MixinRenderLeashKnot {

    @Shadow
    public void doRender(EntityLeashKnot entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    public void func_180559_a(EntityLeashKnot entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
