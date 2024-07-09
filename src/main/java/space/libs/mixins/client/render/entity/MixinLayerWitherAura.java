package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.entity.boss.EntityWither;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(LayerWitherAura.class)
public class MixinLayerWitherAura {

    @Shadow
    public void doRenderLayer(EntityWither entity, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {}

    public void func_177214_a(EntityWither entity, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        this.doRenderLayer(entity, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale);
    }
}
