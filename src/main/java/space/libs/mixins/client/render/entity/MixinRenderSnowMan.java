package space.libs.mixins.client.render.entity;

import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderSnowMan.class)
public abstract class MixinRenderSnowMan {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntitySnowman entity);

    @Shadow
    public abstract ModelSnowMan getMainModel();

    public ModelSnowMan func_177123_g() {
        return this.getMainModel();
    }

    public ResourceLocation func_180587_a(EntitySnowman entity) {
        return this.getEntityTexture(entity);
    }

}
