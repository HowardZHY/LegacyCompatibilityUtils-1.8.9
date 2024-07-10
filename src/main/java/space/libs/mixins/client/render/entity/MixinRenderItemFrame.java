package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.entity.item.EntityItemFrame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderItemFrame.class)
public class MixinRenderItemFrame {

    @Shadow
    protected void renderName(EntityItemFrame entity, double x, double y, double z) {}

    public void func_147914_a(EntityItemFrame entity, double x, double y, double z) {
        this.renderName(entity, x, y, z);
    }
}
