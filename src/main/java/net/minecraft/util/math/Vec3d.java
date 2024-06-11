package net.minecraft.util.math;

import net.minecraft.util.Vec3;

public class Vec3d extends Vec3 {

    public static final Vec3d field_186680_a = new Vec3d(0.0D, 0.0D, 0.0D);

    public Vec3d(double x, double y, double z) {
        super(x, y, z);
    }

    public Vec3d(Vec3i vector) {
        super(vector.getX(), vector.getY(), vector.getZ());
    }

    public boolean equals(Object object) {
        return super.equals(object);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }

    public Vec3d func_186678_a(double p_186678_1_) {
        return new Vec3d(super.xCoord * p_186678_1_, super.yCoord * p_186678_1_, super.zCoord * p_186678_1_);
    }

    public double func_186679_c(double xIn, double yIn, double zIn) {
        double x = xIn - super.xCoord;
        double y = yIn - super.yCoord;
        double z = zIn - super.zCoord;
        return x * x + y * y + z * z;
    }

    public static Vec3d func_189984_a(Vec2f p_189984_0_) {
        return func_189986_a(p_189984_0_.field_189982_i, p_189984_0_.field_189983_j);
    }

    public double func_189985_c() {
        return super.xCoord * super.xCoord + super.yCoord * super.yCoord + super.zCoord * super.zCoord;
    }

    public static Vec3d func_189986_a(float p_189986_0_, float p_189986_1_) {
        float f = MathHelper.cos(-p_189986_1_ * 0.017453292F - 3.1415927F);
        float f1 = MathHelper.sin(-p_189986_1_ * 0.017453292F - 3.1415927F);
        float f2 = -MathHelper.cos(-p_189986_0_ * 0.017453292F);
        float f3 = MathHelper.sin(-p_189986_0_ * 0.017453292F);
        return new Vec3d((f1 * f2), f3, (f * f2));
    }
}
