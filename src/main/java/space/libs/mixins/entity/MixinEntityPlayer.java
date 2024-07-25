package space.libs.mixins.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase {

    public MixinEntityPlayer(World worldIn) {
        super(worldIn);
    }

    @Shadow
    public InventoryPlayer inventory;

    @Shadow
    public abstract ItemStack getHeldItem();

    /** getItemStackFromSlot */
    @Override
    public ItemStack func_184582_a(EntityEquipmentSlot slot) {
        if (slot == EntityEquipmentSlot.MAINHAND) {
            return this.inventory.getCurrentItem();
        } else if (slot.func_188453_a() == EntityEquipmentSlot.Type.ARMOR) {
            return this.inventory.armorInventory[slot.func_188454_b()];
        } else {
            return null;
        }
    }

    @Override
    public ItemStack func_184586_b(EnumHand hand) {
        return this.getHeldItem();
    }

    /** isHandActive */
    @Override
    public boolean func_184587_cr() {
        return (this.getHeldItem() != null);
    }

    @Override
    public EnumHandSide func_184591_cq() {
        return EnumHandSide.RIGHT;
    }
}
