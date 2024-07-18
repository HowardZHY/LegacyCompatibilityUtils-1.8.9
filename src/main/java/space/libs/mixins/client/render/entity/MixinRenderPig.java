package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderPig.class)
public abstract class MixinRenderPig {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityPig entity);

    public ResourceLocation func_180583_a(EntityPig e) {
        return this.getEntityTexture(e);
    }
}
