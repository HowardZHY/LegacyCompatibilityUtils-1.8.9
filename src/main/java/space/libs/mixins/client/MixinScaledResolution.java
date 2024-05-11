package space.libs.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@Mixin(ScaledResolution.class)
public class MixinScaledResolution {

    @ShadowConstructor
    public void ScaledResolution(Minecraft p_i46445_1_) {}

    @NewConstructor
    public void ScaledResolution(Minecraft p_i1094_1_, int field_71443_c, int field_71440_d) {
        ScaledResolution(p_i1094_1_);
    }

    @NewConstructor
    public void ScaledResolution(GameSettings settings, int p_i1094_2_, int p_i1094_3_) {
        ScaledResolution(Minecraft.getMinecraft());
    }
}
