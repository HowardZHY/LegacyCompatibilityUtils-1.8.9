package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import java.util.Map;

@SuppressWarnings("unused")
@Mixin(BlockPart.class)
public class MixinBlockPart {

    @ShadowConstructor
    public void BlockPart(org.lwjgl.util.vector.Vector3f positionFromIn, org.lwjgl.util.vector.Vector3f positionToIn, Map<EnumFacing, BlockPartFace> mapFacesIn, BlockPartRotation partRotationIn, boolean shadeIn) {}

    @NewConstructor
    public void BlockPart(javax.vecmath.Vector3f positionFromIn, javax.vecmath.Vector3f positionToIn, Map<EnumFacing, BlockPartFace> mapFacesIn, BlockPartRotation partRotationIn, boolean shadeIn) {
        this.BlockPart(
            new org.lwjgl.util.vector.Vector3f(
                positionFromIn.x,
                positionFromIn.y,
                positionFromIn.z
            ),
            new org.lwjgl.util.vector.Vector3f(
                positionToIn.x,
                positionToIn.y,
                positionToIn.z
            ),
            mapFacesIn, partRotationIn, shadeIn
        );
    }
}
