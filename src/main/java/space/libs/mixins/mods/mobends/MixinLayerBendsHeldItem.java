package space.libs.mixins.mods.mobends;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@SuppressWarnings("unused, deprecation")
@Pseudo
@Mixin(targets = "net.gobbob.mobends.client.renderer.entity.layers.LayerBendsHeldItem")
public class MixinLayerBendsHeldItem {

}
