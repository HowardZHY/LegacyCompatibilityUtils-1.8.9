package space.libs.mixins.world;

import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraft.world.WorldServer$ServerBlockEventList")
public class MixinServerBlockEventList {

    @ShadowConstructor
    private void ServerBlockEventList() {}

    @NewConstructor
    private void ServerBlockEventList(Object o) {
        ServerBlockEventList();
    }
}
