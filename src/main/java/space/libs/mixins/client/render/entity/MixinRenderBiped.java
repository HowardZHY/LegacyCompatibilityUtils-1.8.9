package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RenderBiped.class)
public abstract class MixinRenderBiped<T extends EntityLiving> extends MixinRenderLiving<T> {

    public MixinRenderBiped(RenderManager renderManager) {
        super(renderManager);
    }

}
