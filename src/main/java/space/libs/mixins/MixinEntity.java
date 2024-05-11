package space.libs.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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
    public double posZ;

    @Shadow
    public World worldObj;

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
            }

            this.inPortal = true;
        }
    }

    /** getTeleportDirection */
    public int func_82148_at() {
        return this.teleportDirection.getHorizontalIndex();
    }

}
