package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderFireball.class)
public abstract class MixinRenderFireball {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityFireball e);

    public ResourceLocation func_180556_a(EntityFireball e) {
        return this.getEntityTexture(e);
    }
}
