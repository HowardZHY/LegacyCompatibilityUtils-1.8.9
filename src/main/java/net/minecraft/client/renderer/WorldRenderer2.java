package net.minecraft.client.renderer;

import net.minecraft.client.renderer.vertex.VertexFormatElement;

@SuppressWarnings("unused")
public final class WorldRenderer2 {

    public static final int[] field_181661_a = new int[8];

    public static int[] a = new int[8];

    static {
        try {
            field_181661_a[VertexFormatElement.EnumType.FLOAT.ordinal()] = 1;
            field_181661_a[VertexFormatElement.EnumType.UINT.ordinal()] = 2;
            field_181661_a[VertexFormatElement.EnumType.INT.ordinal()] = 3;
            field_181661_a[VertexFormatElement.EnumType.USHORT.ordinal()] = 4;
            field_181661_a[VertexFormatElement.EnumType.SHORT.ordinal()] = 5;
            field_181661_a[VertexFormatElement.EnumType.UBYTE.ordinal()] = 6;
            field_181661_a[VertexFormatElement.EnumType.BYTE.ordinal()] = 7;
            a = field_181661_a;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
