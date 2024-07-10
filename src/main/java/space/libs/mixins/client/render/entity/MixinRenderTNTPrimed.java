package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderTNTPrimed.class)
public abstract class MixinRenderTNTPrimed {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityTNTPrimed entity);

    protected ResourceLocation func_180563_a(EntityTNTPrimed entity) {
        return this.getEntityTexture(entity);
    }
}
