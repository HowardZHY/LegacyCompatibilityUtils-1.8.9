package net.minecraft.client.renderer;

import space.libs.util.MappedName;

public final class SwitchTexGen {

    @MappedName("VALUES")
    public static final int[] field_179175_a = new int[8];

    static {
        try {
            field_179175_a[GlStateManager.TexGen.S.ordinal()] = 1;
            field_179175_a[GlStateManager.TexGen.T.ordinal()] = 2;
            field_179175_a[GlStateManager.TexGen.R.ordinal()] = 3;
            field_179175_a[GlStateManager.TexGen.Q.ordinal()] = 4;
        } catch (NoSuchFieldError ignored) {}
    }
}
