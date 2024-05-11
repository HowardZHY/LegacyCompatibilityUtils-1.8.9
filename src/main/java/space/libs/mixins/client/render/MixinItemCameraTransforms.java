package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(ItemCameraTransforms.class)
public class MixinItemCameraTransforms {

    @ShadowConstructor
    public void ItemCameraTransforms(ItemTransformVec3f thirdPersonIn, ItemTransformVec3f firstPersonIn, ItemTransformVec3f headIn, ItemTransformVec3f guiIn, ItemTransformVec3f groundIn, ItemTransformVec3f fixedIn) {}

    @NewConstructor
    public void ItemCameraTransforms(ItemTransformVec3f thirdPersonIn, ItemTransformVec3f firstPersonIn, ItemTransformVec3f headIn, ItemTransformVec3f guiIn, ItemTransformVec3f groundIn) {
        ItemCameraTransforms(thirdPersonIn, firstPersonIn, headIn, guiIn, groundIn, ItemTransformVec3f.DEFAULT);
    }

    @NewConstructor
    public void ItemCameraTransforms(ItemTransformVec3f thirdPersonIn, ItemTransformVec3f firstPersonIn, ItemTransformVec3f headIn, ItemTransformVec3f guiIn) {
        ItemCameraTransforms(thirdPersonIn, firstPersonIn, headIn, guiIn, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT);
    }

}
