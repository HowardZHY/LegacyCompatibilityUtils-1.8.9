package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderCaveSpider;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderCaveSpider.class)
public abstract class MixinRenderCaveSpider {

    @Shadow
    protected void preRenderCallback(EntityCaveSpider entitylivingbaseIn, float partialTickTime) {}

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityCaveSpider entity);

    public void func_180585_a(EntityCaveSpider entitylivingbaseIn, float partialTickTime) {
        this.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    public ResourceLocation func_180586_a(EntityCaveSpider e) {
        return this.getEntityTexture(e);
    }
}
