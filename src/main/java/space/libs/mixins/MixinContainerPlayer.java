package space.libs.mixins;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ContainerPlayer.class)
public abstract class MixinContainerPlayer {

    @Shadow
    private @Final @Mutable EntityPlayer thePlayer;

}
