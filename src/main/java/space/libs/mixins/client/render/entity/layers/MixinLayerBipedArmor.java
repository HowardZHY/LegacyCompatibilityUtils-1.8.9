package space.libs.mixins.client.render.entity.layers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(LayerBipedArmor.class)
public abstract class MixinLayerBipedArmor extends MixinLayerArmorBase {

    @Shadow
    protected void setModelPartVisible(ModelBiped model, int armorSlot) {}

    @Override
    public void func_177195_a(ModelBiped model, int armorSlot) {
        this.setModelPartVisible(model, armorSlot);
    }
}
