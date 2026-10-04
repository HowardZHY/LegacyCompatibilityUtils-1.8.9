package space.libs.mixins.entity;

import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.MappedName;

import java.util.Set;
import java.util.UUID;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow
    public double prevPosX, prevPosZ;

    @Shadow
    public double posX, posY, posZ;

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
    public abstract int getPortalCooldown();

    @Shadow
    public abstract boolean isRiding();

    @Shadow
    public void addChatMessage(IChatComponent component) {}

    /** @implNote Unused in 1.8 ? */
    private UUID persistentID;

    @MappedName("teleportDirection")
    public int field_82152_aq;

    @MappedName(value = "tags", since = "1.9")
    public Set<String> field_184236_aF = Sets.newHashSet();

    @MappedName("setInPortal")
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

    @MappedName("getTeleportDirection")
    public int func_82148_at() {
        return this.field_82152_aq;
    }

    public void func_145747_a(ITextComponent component) {
        this.addChatMessage(component);
    }

    @MappedName(value = "getBoundingBox", since = "1.9")
    public net.minecraft.util.math.AxisAlignedBB func_174813_aQ() {
        return new net.minecraft.util.math.AxisAlignedBB (
            this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ,
            this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ
        );
    }

    @MappedName(value = "getPosition", since = "1.9")
    public net.minecraft.util.math.BlockPos func_180425_c() {
        return new net.minecraft.util.math.BlockPos(this.posX, this.posY + 0.5D, this.posZ);
    }

    @MappedName(value = "removeTag", since = "1.9")
    public boolean func_184197_b(String tag) {
        return this.field_184236_aF.remove(tag);
    }

    @MappedName(value = "addTag", since = "1.9")
    public boolean func_184211_a(String tag) {
        if (this.field_184236_aF.size() >= 1024)
            return false;
        this.field_184236_aF.add(tag);
        return true;
    }

    @MappedName(value = "getTags", since = "1.9")
    public Set<String> func_184216_O() {
        return this.field_184236_aF;
    }

    public boolean func_184218_aH() {
        return this.isRiding();
    }

    @SuppressWarnings("MissingOrInvalidOpcode")
    @Inject(method = "setPortal",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/entity/Entity;teleportDirection:Lnet/minecraft/util/EnumFacing;",
            shift = At.Shift.AFTER
        )
    )
    public void setPortal(BlockPos pos, CallbackInfo ci) {
        this.field_82152_aq = this.teleportDirection.getHorizontalIndex();
    }

    @Inject(method = "copyDataFromOld", at = @At("RETURN"))
    public void copyDataFromOld(Entity entityIn, CallbackInfo ci) {
        this.field_82152_aq = this.teleportDirection.getHorizontalIndex();
    }
}
