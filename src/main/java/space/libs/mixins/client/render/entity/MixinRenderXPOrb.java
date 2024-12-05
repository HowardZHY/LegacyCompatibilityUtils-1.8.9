package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(RenderXPOrb.class)
public abstract class MixinRenderXPOrb {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityXPOrb entity);

    public ResourceLocation func_180555_a(EntityXPOrb xpOrb) {
        return this.getEntityTexture(xpOrb);
    }
}
