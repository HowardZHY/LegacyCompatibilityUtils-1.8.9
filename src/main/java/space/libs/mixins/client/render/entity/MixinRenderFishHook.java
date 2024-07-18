package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.entity.projectile.EntityFishHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderFish.class)
public abstract class MixinRenderFishHook {

    @Shadow
    public void doRender(EntityFishHook entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    public void func_180558_a(EntityFishHook entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
