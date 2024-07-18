package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderCow.class)
public abstract class MixinRenderCow {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityCow entity);

    public ResourceLocation func_180572_a(EntityCow e) {
        return this.getEntityTexture(e);
    }
}
