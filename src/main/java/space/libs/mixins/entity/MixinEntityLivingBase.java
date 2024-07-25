package space.libs.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity {

    public MixinEntityLivingBase(World worldIn) {
        super(worldIn);
    }

    @Shadow
    public abstract ItemStack getHeldItem();

    @Shadow
    public abstract ItemStack getEquipmentInSlot(int slotIn);

    @Shadow
    public abstract Vec3 getLookVec();

    /** getLookVec */
    public Vec3d func_70040_Z() {
        Vec3 v3 = this.getLookVec();
        return new Vec3d(v3.xCoord, v3.yCoord, v3.zCoord);
    }

    /** getItemStackFromSlot */
    public ItemStack func_184582_a(EntityEquipmentSlot slot) {
        if (slot.func_188453_a() == EntityEquipmentSlot.Type.HAND) {
            return this.getHeldItem();
        }
        else return this.getEquipmentInSlot(slot.func_188452_c());
    }

    public ItemStack func_184586_b(EnumHand hand) {
        return this.getHeldItem();
    }

    /** isHandActive */
    public boolean func_184587_cr() {
        return (this.getHeldItem() != null);
    }

    public EnumHandSide func_184591_cq() {
        return EnumHandSide.RIGHT;
    }

    /** getHeldItemOffhand */
    public ItemStack func_184592_cb() {
        return null;
    }

    /** setActiveHand */
    public void func_184598_c(EnumHand hand) {}

    public EnumHand func_184600_cs() {
        return EnumHand.MAIN_HAND;
    }

    /** getItemInUseCount */
    public int func_184605_cv() {
        return 0;
    }

    /** getActiveItemStack */
    public ItemStack func_184607_cu() {
        return this.getHeldItem();
    }

    /** getItemInUseMaxCount */
    public int func_184612_cw() {
        ItemStack stack = this.getHeldItem();
        if (stack != null) return stack.getMaxItemUseDuration();
        else return 0;
    }

    public boolean func_184613_cA() {
        return false;
    }

    /** getHeldItemMainhand */
    public ItemStack func_184614_ca() {
        return this.getHeldItem();
    }
}
