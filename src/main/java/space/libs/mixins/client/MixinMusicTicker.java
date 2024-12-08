package space.libs.mixins.client;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MusicTicker.class)
public abstract class MixinMusicTicker implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

}
