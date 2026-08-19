package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderBat.class)
public abstract class MixinRenderBat {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityBat entity);

    @Shadow
    protected void preRenderCallback(EntityBat entity, float partialTickTime) {}

    public ResourceLocation func_180566_a(EntityBat bat) {
        return this.getEntityTexture(bat);
    }

    public void func_180567_a(EntityBat bat, float f) {
        this.preRenderCallback(bat, f);
    }
}
