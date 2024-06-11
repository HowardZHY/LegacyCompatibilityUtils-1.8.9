package net.minecraft.util.text;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

import java.util.List;
import java.util.stream.Collectors;

public class TextComponentTranslation extends ChatComponentTranslation implements IChatComponent, ITextComponent {
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
        return (ITextComponent) super.setChatStyle((ChatStyle) style);
    }

    public Style func_150256_b() {
        return (Style) super.getChatStyle();
    }

    public ITextComponent func_150257_a(ITextComponent component) {
        return (ITextComponent) super.appendSibling((IChatComponent) component);
    }

    public ITextComponent func_150258_a(String string) {
        return (ITextComponent) super.appendText(string);
    }

    public ITextComponent func_150259_f() {
        return (ITextComponent) super.createCopy();
    }
}
