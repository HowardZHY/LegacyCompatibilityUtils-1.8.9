package net.minecraft.util.text;

import net.minecraft.util.ChatComponentTranslation;

import java.util.List;
import java.util.stream.Collectors;

public class TextComponentTranslation extends ChatComponentTranslation implements ITextComponent {
    public TextComponentTranslation(String translationKey, Object... args) {
        super(translationKey, args);
    }

    public List<ITextComponent> func_150253_a() {
        return super.siblings.stream()
            .filter(component -> component instanceof ITextComponent)
            .map(component -> (ITextComponent) component)
            .collect(Collectors.toList());
    }

    public ITextComponent func_150255_a(Style style) {
        super.setChatStyle(style);
        return (ITextComponent)this;
    }

    public Style func_150256_b() {
        return ((ITextComponent)this).func_150256_b();
    }

    public ITextComponent func_150257_a(ITextComponent paramITextComponent) {
        return (ITextComponent)this;
    }

    public ITextComponent func_150258_a(String paramString) {
        return (ITextComponent)this;
    }

    public TextComponentTranslation func_150259_f() {
        super.createCopy();
        return this;
    }
}
