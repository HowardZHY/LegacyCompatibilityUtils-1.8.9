package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderSlime.class)
public abstract class MixinRenderSlime {

    @Shadow
    public void doRender(EntitySlime entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    public void func_177124_a(EntitySlime entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
