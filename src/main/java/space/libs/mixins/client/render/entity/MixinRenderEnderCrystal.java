package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderEnderCrystal.class)
public abstract class MixinRenderEnderCrystal {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityEnderCrystal entity);

    public ResourceLocation func_180554_a(EntityEnderCrystal e) {
        return this.getEntityTexture(e);
    }
}
