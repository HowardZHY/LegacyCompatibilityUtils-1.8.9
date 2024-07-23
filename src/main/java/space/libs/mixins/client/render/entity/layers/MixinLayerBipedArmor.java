package space.libs.mixins.client.render.entity.layers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(LayerBipedArmor.class)
public abstract class MixinLayerBipedArmor extends MixinLayerArmorBase<ModelBiped> {

    @Override
    public void func_177195_a(ModelBiped model, int armorSlot) {
        this.setModelPartVisible(model, armorSlot);
    }
}
