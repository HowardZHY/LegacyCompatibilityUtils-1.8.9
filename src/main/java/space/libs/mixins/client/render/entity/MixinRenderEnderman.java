package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderEnderman.class)
public abstract class MixinRenderEnderman {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityEnderman entity);

    public ResourceLocation func_180573_a(EntityEnderman entity) {
        return this.getEntityTexture(entity);
    }
}
