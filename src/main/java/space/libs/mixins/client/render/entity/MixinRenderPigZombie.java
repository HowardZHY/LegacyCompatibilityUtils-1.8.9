package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderPigZombie;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderPigZombie.class)
public abstract class MixinRenderPigZombie {

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityPigZombie entity);

    public ResourceLocation func_177119_a(EntityPigZombie entity) {
        return this.getEntityTexture(entity);
    }
}
