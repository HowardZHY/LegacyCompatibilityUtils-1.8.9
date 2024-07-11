package space.libs.mixins.client;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SoundHandler.class)
public class MixinSoundHandler implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
