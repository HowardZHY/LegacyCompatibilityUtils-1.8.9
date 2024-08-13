package space.libs.mixins.client;

import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraft.client.gui.GuiPlayerTabOverlay$PlayerComparator")
public abstract class MixinGuiPlayerTabOverlayPlayerComparator {

    @Shadow
    public abstract int compare(NetworkPlayerInfo p_compare_1_, NetworkPlayerInfo p_compare_2_);

    public int func_178952_a(NetworkPlayerInfo p_compare_1_, NetworkPlayerInfo p_compare_2_) {
        return this.compare(p_compare_1_, p_compare_2_);
    }
}
