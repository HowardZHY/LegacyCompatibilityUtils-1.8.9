package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ITransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import javax.vecmath.*;

@Mixin(FaceBakery.class)
public abstract class MixinFaceBakery {

    @Shadow(remap = false)
    public abstract int rotateVertex(org.lwjgl.util.vector.Vector3f position, EnumFacing facing, int vertexIndex, ITransformation modelRotationIn, boolean uvLocked);

    @Shadow(remap = false)
    public abstract BakedQuad makeBakedQuad(org.lwjgl.util.vector.Vector3f posFrom, org.lwjgl.util.vector.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, net.minecraftforge.client.model.ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade);

    @Shadow
    public abstract BakedQuad makeBakedQuad(org.lwjgl.util.vector.Vector3f posFrom, org.lwjgl.util.vector.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ModelRotation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade);

    public int rotateVertex(Vector3d position, EnumFacing facing, int vertexIndex, ITransformation modelRotationIn, boolean uvLocked) {
        return this.rotateVertex(TransformVec3dto3f(position), facing, vertexIndex, modelRotationIn, uvLocked);
    }

    public BakedQuad makeBakedQuad(javax.vecmath.Vector3f posFrom, javax.vecmath.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, net.minecraftforge.client.model.ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        return this.makeBakedQuad(TransformVec3f(posFrom), TransformVec3f(posTo), face, sprite, facing, modelRotationIn, partRotation, uvLocked, shade);
    }

    public BakedQuad func_178414_a(javax.vecmath.Vector3f posFrom, javax.vecmath.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ModelRotation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        return this.makeBakedQuad(TransformVec3f(posFrom), TransformVec3f(posTo), face, sprite, facing, modelRotationIn, partRotation, uvLocked, shade);
    }

    @Public
    private static org.lwjgl.util.vector.Vector3f TransformVec3f(javax.vecmath.Vector3f vec) {
        return new org.lwjgl.util.vector.Vector3f(vec.x, vec.y, vec.z);
    }

    @Public
    private static org.lwjgl.util.vector.Vector3f TransformVec3dto3f(javax.vecmath.Vector3d vec) {
        return new org.lwjgl.util.vector.Vector3f((float) vec.x, (float) vec.y, (float) vec.z);
    }

}
