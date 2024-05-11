package net.minecraft.client.renderer;

import net.minecraft.client.renderer.vertex.VertexFormatElement;

public final class SwitchEnumUsage {

    /** VALUES */
    public static final int[] field_178959_a = new int[VertexFormatElement.EnumUsage.values().length];

    static {
        try {
            field_178959_a[VertexFormatElement.EnumUsage.POSITION.ordinal()] = 1;
        } catch (NoSuchFieldError ignored) {
        }
        try {
            field_178959_a[VertexFormatElement.EnumUsage.COLOR.ordinal()] = 2;
        } catch (NoSuchFieldError ignored) {
        }
        try {
            field_178959_a[VertexFormatElement.EnumUsage.UV.ordinal()] = 3;
        } catch (NoSuchFieldError ignored) {
        }
        try {
            field_178959_a[VertexFormatElement.EnumUsage.NORMAL.ordinal()] = 4;
        } catch (NoSuchFieldError ignored) {
        }
    }

}
