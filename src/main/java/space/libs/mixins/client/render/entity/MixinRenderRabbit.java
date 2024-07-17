package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderRabbit;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderRabbit.class)
public abstract class MixinRenderRabbit {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityRabbit entity);

    public ResourceLocation func_177125_a(EntityRabbit entity) {
        return this.getEntityTexture(entity);
    }
}
