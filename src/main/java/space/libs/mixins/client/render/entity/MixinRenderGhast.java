package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderGhast.class)
public abstract class MixinRenderGhast {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityGhast entity);

    public ResourceLocation func_180576_a(EntityGhast e) {
        return this.getEntityTexture(e);
    }
}
