package net.minecraft.util.text;

import net.minecraft.util.IChatComponent;

import java.util.List;

@SuppressWarnings("unused")
public interface ITextComponent extends IChatComponent {
    ITextComponent func_150255_a(Style paramStyle);

    Style func_150256_b();

    ITextComponent func_150258_a(String paramString);

    ITextComponent func_150257_a(ITextComponent paramITextComponent);

    /** func_150261_e() */
    String getUnformattedTextForChat();

    /** func_150260_c() */
    String getUnformattedText();

    /** func_150254_d */
    String getFormattedText();

    List<ITextComponent> func_150253_a();

    ITextComponent func_150259_f();
}
