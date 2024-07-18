package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderWolf.class)
public abstract class MixinRenderWolf {

    @Shadow
    public void doRender(EntityWolf entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    @Shadow
    protected abstract float handleRotationFloat(EntityWolf livingBase, float partialTicks);

    public void func_177135_a(EntityWolf entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public float func_180593_a(EntityWolf livingBase, float partialTicks) {
        return this.handleRotationFloat(livingBase, partialTicks);
    }
}
