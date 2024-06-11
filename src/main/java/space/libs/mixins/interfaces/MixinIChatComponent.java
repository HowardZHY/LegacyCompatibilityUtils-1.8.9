package space.libs.mixins.interfaces;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(IChatComponent.class)
public interface MixinIChatComponent extends ITextComponent {

    ITextComponent func_150255_a(Style paramStyle);

    Style func_150256_b();

    ITextComponent func_150258_a(String paramString);

    ITextComponent func_150257_a(ITextComponent paramITextComponent);

    /** func_150261_e() */
    @Shadow
    String getUnformattedTextForChat();

    /** func_150260_c() */
    @Shadow
    String getUnformattedText();

    /** func_150254_d */
    @Shadow
    String getFormattedText();

    List<ITextComponent> func_150253_a();

    ITextComponent func_150259_f();

}
