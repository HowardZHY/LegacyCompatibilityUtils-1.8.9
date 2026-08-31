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
import space.libs.util.MappedName;


@SuppressWarnings("all")
public class IEntitySelector extends EntitySelectors {

    @MappedName("selectAnything")
    public static Predicate<Entity> field_94557_a = new Predicate<Entity>() {

        public boolean func_180131_a(Entity entity) {
            return entity.isEntityAlive();
        }

        @Override
        public boolean apply(Entity entity) {
            return this.func_180131_a(entity);
        }
    };

    @MappedName("IS_STANDALONE")
    public static Predicate<Entity> field_152785_b  = new Predicate<Entity>() {

        public boolean func_180130_a(Entity entity) {
            return entity.isEntityAlive() && entity.riddenByEntity == null && entity.ridingEntity == null;
        }

        @Override
        public boolean apply(Entity entity) {
            return this.func_180130_a(entity);
        }
    };

    @MappedName("selectInventories")
    public static Predicate<Entity> field_96566_b = new Predicate<Entity>() {

        public boolean func_180102_a(Entity entity) {
            return entity instanceof IInventory && entity.isEntityAlive();
        }

        @Override
        public boolean apply(Entity entity) {
            return this.func_180102_a(entity);
        }
    };

    @MappedName("NOT_SPECTATING")
    public static Predicate<Entity> field_180132_d = new Predicate<Entity>() {

        public boolean func_180103_a(Entity entity) {
            return !(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator();
        }

        @Override
        public boolean apply(Entity entity) {
            return this.func_180103_a(entity);
        }
    };

    public static class ArmoredMob implements Predicate<Entity> {

        public ItemStack field_96567_c;

        public ArmoredMob(ItemStack stack)
        {
            this.field_96567_c = stack;
        }

        public boolean func_180100_a(Entity entity) {
            if (!entity.isEntityAlive()) {
                return false;
            } else if (!(entity instanceof EntityLivingBase)) {
                return false;
            } else {
                EntityLivingBase elb = (EntityLivingBase)entity;
                return elb.getEquipmentInSlot(EntityLiving.getArmorPosition(this.field_96567_c)) == null && (elb instanceof EntityLiving ? ((EntityLiving) elb).canPickUpLoot() : (elb instanceof EntityArmorStand || elb instanceof EntityPlayer));
            }
        }

        @Override
        public boolean apply(Entity entity) {
            return this.func_180100_a(entity);
        }
    }
}
