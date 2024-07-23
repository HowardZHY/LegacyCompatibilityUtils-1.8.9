package space.libs.mixins;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ItemArmor.class)
public abstract class MixinItemArmor {

    @Shadow
    public @Final int armorType;

    public EntityEquipmentSlot func_185083_B_() {
        return EntityEquipmentSlot.fromArmorIndex(armorType);
    }
}
