package net.minecraft.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

@SuppressWarnings("all")
public enum EnumHandSide {
    LEFT((ITextComponent) new TextComponentTranslation("options.mainHand.left", new Object[0])),

    RIGHT((ITextComponent) new TextComponentTranslation("options.mainHand.right", new Object[0]));

    public ITextComponent field_188471_c;

    EnumHandSide(ITextComponent p_i46806_3_) {
        this.field_188471_c = p_i46806_3_;
    }

    public EnumHandSide func_188468_a() {
        if (this == LEFT)
            return RIGHT;
        return LEFT;
    }

    public String toString() {
        return this.field_188471_c.getUnformattedText();
    }
}
