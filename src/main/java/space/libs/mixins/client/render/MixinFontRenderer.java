package space.libs.mixins.client.render;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(FontRenderer.class)
public abstract class MixinFontRenderer {

    @Shadow
    private float renderChar(char ch, boolean italic) {
        throw new AbstractMethodError();
    }

    /** renderCharAtPos */
    private float func_78278_a(int p_78278_1_, char ch, boolean italic) {
        return this.renderChar(ch, italic);
    }

}
