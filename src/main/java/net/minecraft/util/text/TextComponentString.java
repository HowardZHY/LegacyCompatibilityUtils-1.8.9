package net.minecraft.util.text;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.util.List;
import java.util.stream.Collectors;

public class TextComponentString extends ChatComponentText implements IChatComponent, ITextComponent {

    public TextComponentString(String msg) {
        super(msg);
    }

    public List<ITextComponent> func_150253_a() {
        return super.siblings.stream()
            .filter(component -> component instanceof ITextComponent)
            .map(component -> (ITextComponent) component)
            .collect(Collectors.toList());
    }

    public ITextComponent func_150255_a(Style paramStyle) {
        return this;
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

    public ITextComponent func_150259_f() {
        super.createCopy();
        return this;
    }
}
