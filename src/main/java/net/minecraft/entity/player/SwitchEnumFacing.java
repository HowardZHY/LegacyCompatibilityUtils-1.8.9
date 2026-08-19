package net.minecraft.entity.player;

import net.minecraft.util.EnumFacing;
import space.libs.util.MappedName;

public final class SwitchEnumFacing {

    @MappedName("VALUES")
    public static final int[] field_179420_a = new int[6];

    static {
        try {
            field_179420_a[EnumFacing.SOUTH.ordinal()] = 1;
            field_179420_a[EnumFacing.NORTH.ordinal()] = 2;
            field_179420_a[EnumFacing.WEST.ordinal()] = 3;
            field_179420_a[EnumFacing.EAST.ordinal()] = 4;
        } catch (NoSuchFieldError ignored) {}
    }
}
