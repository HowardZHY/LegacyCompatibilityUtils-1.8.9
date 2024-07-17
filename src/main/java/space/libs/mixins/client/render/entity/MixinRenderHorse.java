package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderHorse.class)
public abstract class MixinRenderHorse {

    @Shadow
    protected void preRenderCallback(EntityHorse entitylivingbaseIn, float partialTickTime) {}

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityHorse horse);

    public void func_180580_a(EntityHorse entitylivingbaseIn, float partialTickTime) {
        this.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    public ResourceLocation func_180581_a(EntityHorse horse) {
        return this.getEntityTexture(horse);
    }
}
