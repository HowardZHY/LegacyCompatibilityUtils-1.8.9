package space.libs.mixins.client;

import net.minecraft.client.audio.GuardianSound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuardianSound.class)
public abstract class MixinGuardianSound extends MixinMovingSound implements IUpdatePlayerListBox {

    @Shadow
    @Override
    public void update() {}

}
