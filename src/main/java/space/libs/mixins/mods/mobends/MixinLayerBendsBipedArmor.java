package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.client.model.entity.ModelBendsBipedArmor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unchecked, rawtypes")
@Pseudo
@Mixin(targets = "net.gobbob.mobends.client.renderer.entity.layers.LayerBendsBipedArmor")
public abstract class MixinLayerBendsBipedArmor extends LayerArmorBase {

    public MixinLayerBendsBipedArmor(RendererLivingEntity rendererIn) {
        super(rendererIn);
    }

    @Shadow(remap = false)
    protected void setModelSlotVisible(ModelBendsBipedArmor model, EntityEquipmentSlot p_188359_2_) {}

    @Override
    protected void setModelPartVisible(ModelBase model, int armorSlot) {
        this.setModelSlotVisible((ModelBendsBipedArmor) model, EntityEquipmentSlot.fromArmorSlotIndex(armorSlot));
    }
}
