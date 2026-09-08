package space.libs.mixins.client;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MovingSound.class)
public abstract class MixinMovingSound implements IUpdatePlayerListBox {

    @SuppressWarnings("RedundantMethodOverride")
    @Override
    public void update() {}

}
