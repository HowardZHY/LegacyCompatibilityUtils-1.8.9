package space.libs.mixins.client.gui;

import net.minecraft.client.gui.GuiScreenRealmsProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiScreenRealmsProxy.class)
public abstract class MixinGuiScreenRealmsProxy {

    @Shadow
    public void func_154322_b(String text, int x, int y, int color, boolean b) {}

    public void func_154322_b(String text, int x, int y, int color) {
        this.func_154322_b(text, x, y, color, true);
    }
}
