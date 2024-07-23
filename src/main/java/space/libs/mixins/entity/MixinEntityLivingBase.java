package space.libs.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
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

    /** setActiveHand */
    public void func_184598_c(EnumHand hand) {

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

}
