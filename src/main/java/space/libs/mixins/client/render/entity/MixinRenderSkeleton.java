package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderSkeleton.class)
public abstract class MixinRenderSkeleton {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntitySkeleton entity);

    protected ResourceLocation func_180577_a(EntitySkeleton entity) {
        return this.getEntityTexture(entity);
    }
}
