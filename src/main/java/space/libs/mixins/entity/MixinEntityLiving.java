package space.libs.mixins.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityLiving.class)
public abstract class MixinEntityLiving extends MixinEntityLivingBase {

    public MixinEntityLiving(World worldIn) {
        super(worldIn);
    }

    @Shadow
    public void setCurrentItemOrArmor(int slotIn, ItemStack stack) {}

    @Override
    public EnumHandSide func_184591_cq() {
        return EnumHandSide.RIGHT;
    }

    /** setItemStackToSlot */
    public void func_184201_a(EntityEquipmentSlot slot, ItemStack stack) {
        if (slot.func_188453_a() == EntityEquipmentSlot.Type.HAND) {
            this.setCurrentItemOrArmor(0, stack);
            return;
        }
        if (slot.func_188453_a() == EntityEquipmentSlot.Type.ARMOR) {
            this.setCurrentItemOrArmor(slot.func_188452_c(), stack);
        }
    }
}
