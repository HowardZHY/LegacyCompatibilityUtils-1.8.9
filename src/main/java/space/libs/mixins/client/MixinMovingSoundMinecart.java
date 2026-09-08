package space.libs.mixins.client;

import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MovingSoundMinecart.class)
public class MixinMovingSoundMinecart extends MixinMovingSound implements IUpdatePlayerListBox {

    @Shadow
    public void update() {}

}
