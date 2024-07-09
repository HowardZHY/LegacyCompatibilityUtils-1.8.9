package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderChicken.class)
public abstract class MixinRenderChicken {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityChicken entity);

    @Shadow
    protected abstract float handleRotationFloat(EntityChicken livingBase, float partialTicks);

    public ResourceLocation func_180568_a(EntityChicken entity) {
        return this.getEntityTexture(entity);
    }

    public float func_180569_a(EntityChicken livingBase, float partialTicks) {
        return this.handleRotationFloat(livingBase, partialTicks);
    }
}
