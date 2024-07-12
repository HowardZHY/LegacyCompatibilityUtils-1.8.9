package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderPainting.class)
public abstract class MixinRenderPainting {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityPainting e);

    public ResourceLocation func_180562_a(EntityPainting e) {
        return this.getEntityTexture(e);
    }
}
