package space.libs.mixins.client;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Shadow
    public void renderSelectedItem(ScaledResolution scaledRes) {}

    public void func_175182_a(ScaledResolution resolution) {
        this.renderSelectedItem(resolution);
    }
}
