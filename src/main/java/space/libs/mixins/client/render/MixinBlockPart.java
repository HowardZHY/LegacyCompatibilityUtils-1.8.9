package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.*;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.client.IMathUtils;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import java.util.Map;

import static space.libs.util.client.IMathUtils.*;

@SuppressWarnings("unused")
@Mixin(BlockPart.class)
public class MixinBlockPart implements IMathUtils {

    @ShadowConstructor
    public void BlockPart(org.lwjgl.util.vector.Vector3f positionFromIn, org.lwjgl.util.vector.Vector3f positionToIn, Map<EnumFacing, BlockPartFace> mapFacesIn, BlockPartRotation partRotationIn, boolean shadeIn) {}

    @NewConstructor
    public void BlockPart(javax.vecmath.Vector3f positionFromIn, javax.vecmath.Vector3f positionToIn, Map<EnumFacing, BlockPartFace> mapFacesIn, BlockPartRotation partRotationIn, boolean shadeIn) {
        BlockPart(TransformVector3f(positionFromIn), TransformVector3f(positionToIn), mapFacesIn, partRotationIn, shadeIn);
    }
}
