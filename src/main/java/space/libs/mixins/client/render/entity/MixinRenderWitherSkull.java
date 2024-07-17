package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderWitherSkull.class)
public abstract class MixinRenderWitherSkull {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityWitherSkull entity);

    public ResourceLocation func_180564_a(EntityWitherSkull entity) {
        return this.getEntityTexture(entity);
    }
}
