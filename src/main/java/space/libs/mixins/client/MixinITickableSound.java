package space.libs.mixins.client;

import net.minecraft.client.audio.ITickableSound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ITickableSound.class)
public interface MixinITickableSound extends IUpdatePlayerListBox {

}
