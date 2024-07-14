package space.libs.mixins.client.render.entity;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderVillager.class)
public abstract class MixinRenderVillager {

    @Shadow
    public abstract ModelVillager getMainModel();

    public ModelVillager func_177134_g() {
        return this.getMainModel();
    }
}
