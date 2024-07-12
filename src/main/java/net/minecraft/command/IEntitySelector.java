package net.minecraft.command;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;


@SuppressWarnings("all")
public class IEntitySelector extends EntitySelectors {

    /** field_94557_a */
    public static Predicate<Entity> selectAnything = new Predicate<Entity>() {

        public boolean func_180131_a(Entity p_180131_1_)
        {
            return p_180131_1_.isEntityAlive();
        }

        public boolean apply(Entity p_apply_1_)
        {
            return this.func_180131_a(p_apply_1_);
        }

    };

    /** field_152785_b */
    public static Predicate<Entity> IS_STANDALONE = new Predicate<Entity>() {

        public boolean func_180130_a(Entity p_180130_1_)
        {
            return p_180130_1_.isEntityAlive() && p_180130_1_.riddenByEntity == null && p_180130_1_.ridingEntity == null;
        }

        public boolean apply(Entity p_apply_1_)
        {
            return this.func_180130_a(p_apply_1_);
        }

    };

    /** field_96566_b */
    public static Predicate<Entity> selectInventories = new Predicate<Entity>() {

        public boolean func_180102_a(Entity p_180102_1_)
        {
            return p_180102_1_ instanceof IInventory && p_180102_1_.isEntityAlive();
        }

        public boolean apply(Entity p_apply_1_)
        {
            return this.func_180102_a(p_apply_1_);
        }

    };

    /** field_180132_d */
    public static Predicate<Entity> NOT_SPECTATING = new Predicate<Entity>() {

        public boolean func_180103_a(Entity p_180103_1_)
        {
            return !(p_180103_1_ instanceof EntityPlayer) || !((EntityPlayer)p_180103_1_).isSpectator();
        }

        public boolean apply(Entity p_apply_1_)
        {
            return this.func_180103_a(p_apply_1_);
        }

    };


    public static class ArmoredMob implements Predicate<Entity> {

        public ItemStack field_96567_c;


        public ArmoredMob(ItemStack p_i1584_1_)
        {
            this.field_96567_c = p_i1584_1_;
        }

        public boolean func_180100_a(Entity p_180100_1_)
        {
            if (!p_180100_1_.isEntityAlive()) {
                return false;
            } else if (!(p_180100_1_ instanceof EntityLivingBase)) {
                return false;
            } else {
                EntityLivingBase elb = (EntityLivingBase)p_180100_1_;
                return elb.getEquipmentInSlot(EntityLiving.getArmorPosition(this.field_96567_c)) == null && (elb instanceof EntityLiving ? ((EntityLiving) elb).canPickUpLoot() : (elb instanceof EntityArmorStand || elb instanceof EntityPlayer));
            }
        }

        public boolean apply(Entity p_apply_1_)
        {
            return this.func_180100_a(p_apply_1_);
        }

    }
}
