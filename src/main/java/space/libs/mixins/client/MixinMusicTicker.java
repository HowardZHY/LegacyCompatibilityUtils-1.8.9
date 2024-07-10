package space.libs.mixins.client;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MusicTicker.class)
public class MixinMusicTicker implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
