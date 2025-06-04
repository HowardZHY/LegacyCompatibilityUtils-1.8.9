package net.minecraft.client.renderer;

import net.minecraft.client.renderer.vertex.VertexFormatElement;
import space.libs.util.MappedName;

public final class SwitchEnumUsage {

    @MappedName("VALUES")
    public static final int[] field_178959_a = new int[8];

    static {
        try {
            field_178959_a[VertexFormatElement.EnumUsage.POSITION.ordinal()] = 1;
            field_178959_a[VertexFormatElement.EnumUsage.COLOR.ordinal()] = 2;
            field_178959_a[VertexFormatElement.EnumUsage.UV.ordinal()] = 3;
            field_178959_a[VertexFormatElement.EnumUsage.NORMAL.ordinal()] = 4;
        } catch (NoSuchFieldError ignored) {}
    }
}
