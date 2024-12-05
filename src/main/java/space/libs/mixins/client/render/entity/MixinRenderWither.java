package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.boss.EntityWither;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderWither.class)
public abstract class MixinRenderWither {

    @Shadow
    public void doRender(EntityWither entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    @Shadow
    protected void preRenderCallback(EntityWither entityWither, float partialTickTime) {}

    public void func_180591_a(EntityWither entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void func_180592_a(EntityWither entity, float partialTickTime) {
        this.preRenderCallback(entity, partialTickTime);
    }

}
