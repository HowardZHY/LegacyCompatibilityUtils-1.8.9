package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderEndermite;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderEndermite.class)
public abstract class MixinRenderEndermite {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityEndermite entity);

    @Shadow
    protected abstract float getDeathMaxRotation(EntityEndermite entityLivingBaseIn);

    public ResourceLocation func_177106_b(EntityEndermite e) {
        return this.getEntityTexture(e);
    }

    public float func_177107_a(EntityEndermite e) {
        return this.getDeathMaxRotation(e);
    }

}
