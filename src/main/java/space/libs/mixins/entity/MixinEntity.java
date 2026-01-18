package space.libs.mixins.entity;

import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.MappedName;

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

    @Shadow
    public abstract boolean isRiding();

    @Shadow
    public void addChatMessage(IChatComponent component) {}

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
    public boolean func_184197_b(String p_184197_1_) {
        return this.field_184236_aF.remove(p_184197_1_);
    }

    @MappedName(value = "addTag", since = "1.9")
    public boolean func_184211_a(String p_184211_1_) {
        if (this.field_184236_aF.size() >= 1024)
            return false;
        this.field_184236_aF.add(p_184211_1_);
        return true;
    }

    @MappedName(value = "getTags", since = "1.9")
    public Set<String> func_184216_O() {
        return this.field_184236_aF;
    }

    public boolean func_184218_aH() {
        return this.isRiding();
    }
}
