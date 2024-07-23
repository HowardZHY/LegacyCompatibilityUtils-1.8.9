package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused, deprecation")
@Mixin(ItemCameraTransforms.TransformType.class)
public class MixinItemCameraTransformsTransformType {

    @Shadow
    public static @Final ItemCameraTransforms.TransformType THIRD_PERSON;

    @Shadow
    public static @Final ItemCameraTransforms.TransformType FIRST_PERSON;

    @Public
    private static final ItemCameraTransforms.TransformType THIRD_PERSON_RIGHT_HAND = THIRD_PERSON;

    @Public
    private static final ItemCameraTransforms.TransformType THIRD_PERSON_LEFT_HAND = THIRD_PERSON;

    @Public
    private static final ItemCameraTransforms.TransformType FIRST_PERSON_RIGHT_HAND = FIRST_PERSON;

    @Public
    private static final ItemCameraTransforms.TransformType FIRST_PERSON_LEFT_HAND = FIRST_PERSON;

}
