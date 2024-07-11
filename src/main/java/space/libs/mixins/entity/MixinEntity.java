package space.libs.mixins.entity;

import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@SuppressWarnings("all")
@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow
    public double prevPosX;

    @Shadow
    public double prevPosZ;

    @Shadow
    public double posX;

    @Shadow
    public double posY;

    @Shadow
    public double posZ;

    @Shadow
    public World worldObj;

    @Shadow
    private AxisAlignedBB boundingBox;

    @Shadow
    protected boolean inPortal;

    @Shadow
    protected EnumFacing teleportDirection;

    @Shadow
    public int timeUntilPortal;

    @Shadow
    public int getPortalCooldown() {
        return 300;
    }

    /** teleportDirection */
    public int field_82152_aq;

    /** tags */
    public Set<String> field_184236_aF = Sets.newHashSet();

    /** setInPortal */
    public void func_70063_aa() {
        if (this.timeUntilPortal > 0) {
            this.timeUntilPortal = this.getPortalCooldown();
        } else {
            double x = this.prevPosX - this.posX;
            double y = this.prevPosZ - this.posZ;

            if (!this.worldObj.isRemote && !this.inPortal) {
                int facing;

                if (MathHelper.abs((float) x) > MathHelper.abs((float) y)) {
                    facing = x > 0.0D ? EnumFacing.WEST.getHorizontalIndex() : EnumFacing.EAST.getHorizontalIndex();
                } else {
                    facing = y > 0.0D ? EnumFacing.NORTH.getHorizontalIndex() : EnumFacing.SOUTH.getHorizontalIndex();
                }
                this.teleportDirection = EnumFacing.getHorizontal(facing);
                this.field_82152_aq = this.teleportDirection.getHorizontalIndex();
            }

            this.inPortal = true;
        }
    }

    /** getTeleportDirection */
    public int func_82148_at() {
        return this.field_82152_aq;
    }

    /** getBoundingBox */
    public net.minecraft.util.math.AxisAlignedBB func_174813_aQ() {
        return (net.minecraft.util.math.AxisAlignedBB) this.boundingBox;
    }

    /** getPosition */
    public net.minecraft.util.math.BlockPos func_180425_c() {
        return new net.minecraft.util.math.BlockPos(this.posX, this.posY + 0.5D, this.posZ);
    }

    /** removeTag */
    public boolean func_184197_b(String p_184197_1_) {
        return this.field_184236_aF.remove(p_184197_1_);
    }

    /** addTag */
    public boolean func_184211_a(String p_184211_1_) {
        if (this.field_184236_aF.size() >= 1024)
            return false;
        this.field_184236_aF.add(p_184211_1_);
        return true;
    }

    /** getTags */
    public Set<String> func_184216_O() {
        return this.field_184236_aF;
    }

}
