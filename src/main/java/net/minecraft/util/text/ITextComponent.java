package net.minecraft.util.text;

import net.minecraft.util.IChatComponent;

import java.util.List;

public interface ITextComponent extends IChatComponent {

    ITextComponent func_150255_a(Style style);

    Style func_150256_b();

    ITextComponent func_150258_a(String text);

    ITextComponent func_150257_a(ITextComponent component);

    /** func_150261_e() */
    @Override
    String getUnformattedTextForChat();

    /** func_150260_c() */
    @Override
    String getUnformattedText();

    /** func_150254_d */
    @Override
    String getFormattedText();

    List<ITextComponent> func_150253_a();

    ITextComponent func_150259_f();

}
