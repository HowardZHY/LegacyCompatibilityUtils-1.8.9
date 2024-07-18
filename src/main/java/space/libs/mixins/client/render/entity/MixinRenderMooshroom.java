package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderMooshroom.class)
public abstract class MixinRenderMooshroom {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityMooshroom entity);

    public ResourceLocation func_180582_a(EntityMooshroom e) {
        return this.getEntityTexture(e);
    }
}
