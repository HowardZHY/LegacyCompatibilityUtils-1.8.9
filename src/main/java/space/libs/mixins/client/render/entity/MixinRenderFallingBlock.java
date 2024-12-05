package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.entity.item.EntityFallingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderFallingBlock.class)
public abstract class MixinRenderFallingBlock {
    @Shadow
    public void doRender(EntityFallingBlock entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    public void func_180557_a(EntityFallingBlock entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
