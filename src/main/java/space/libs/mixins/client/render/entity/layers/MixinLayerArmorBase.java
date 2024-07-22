package space.libs.mixins.client.render.entity.layers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(LayerArmorBase.class)
public abstract class MixinLayerArmorBase {

    @Shadow
    protected abstract void setModelPartVisible(ModelBase model, int armorSlot);

    public void func_177195_a(ModelBiped p_177195_1_, int p_177195_2_) {
        this.setModelPartVisible(p_177195_1_, p_177195_2_);
    }
}
