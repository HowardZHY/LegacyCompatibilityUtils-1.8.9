package space.libs.mixins.client;

import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraft.client.gui.GuiPlayerTabOverlay$PlayerComparator")
public abstract class MixinGuiPlayerTabOverlayPlayerComparator {

    @Shadow
    public abstract int compare(NetworkPlayerInfo info1, NetworkPlayerInfo info2);

    public int func_178952_a(NetworkPlayerInfo info1, NetworkPlayerInfo info2) {
        return this.compare(info1, info2);
    }
}
