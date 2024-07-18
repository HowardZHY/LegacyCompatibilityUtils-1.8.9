package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.monster.EntitySilverfish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderSilverfish.class)
public abstract class MixinRenderSilverfish {

    @Shadow
    protected abstract float getDeathMaxRotation(EntitySilverfish entityLivingBaseIn);


    public float func_180584_a(EntitySilverfish entityLivingBaseIn) {
        return this.getDeathMaxRotation(entityLivingBaseIn);
    }
}
