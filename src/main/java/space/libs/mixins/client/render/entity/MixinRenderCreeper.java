package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.monster.EntityCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderCreeper.class)
public abstract class MixinRenderCreeper {

    @Shadow
    protected abstract void preRenderCallback(EntityCreeper entitylivingbaseIn, float partialTickTime);

    @Shadow
    protected abstract int getColorMultiplier(EntityCreeper entitylivingbaseIn, float lightBrightness, float partialTickTime);

    public void func_180570_a(EntityCreeper entitylivingbaseIn, float partialTickTime) {
        this.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    public int func_180571_a(EntityCreeper entitylivingbaseIn, float lightBrightness, float partialTickTime) {
        return this.getColorMultiplier(entitylivingbaseIn, lightBrightness, partialTickTime);
    }
}
