package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ITransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(FaceBakery.class)
public abstract class MixinFaceBakery {

    @Shadow(remap = false)
    public abstract int rotateVertex(org.lwjgl.util.vector.Vector3f position, EnumFacing facing, int vertexIndex, ITransformation modelRotationIn, boolean uvLocked);

    @Shadow
    private void storeVertexData(int[] faceData, int storeIndex, int vertexIndex, org.lwjgl.util.vector.Vector3f position, int shadeColor, TextureAtlasSprite sprite, BlockFaceUV faceUV) {}

    @Shadow(remap = false)
    public abstract BakedQuad makeBakedQuad(org.lwjgl.util.vector.Vector3f posFrom, org.lwjgl.util.vector.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, net.minecraftforge.client.model.ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade);

    @Shadow
    public abstract BakedQuad makeBakedQuad(org.lwjgl.util.vector.Vector3f posFrom, org.lwjgl.util.vector.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ModelRotation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade);

    @Shadow
    private float[] getPositionsDiv16(org.lwjgl.util.vector.Vector3f pos1, org.lwjgl.util.vector.Vector3f pos2) {
        throw new AbstractMethodError();
    }

    @Shadow
    private void rotatePart(org.lwjgl.util.vector.Vector3f p_178407_1_, BlockPartRotation partRotation) {}

    @Shadow
    private void rotateScale(org.lwjgl.util.vector.Vector3f position, org.lwjgl.util.vector.Vector3f rotationOrigin, org.lwjgl.util.vector.Matrix4f rotationMatrix, org.lwjgl.util.vector.Vector3f scale) {}

    public int rotateVertex(javax.vecmath.Vector3d position, EnumFacing facing, int vertexIndex, ITransformation modelRotationIn, boolean uvLocked) {
        return this.rotateVertex(TransformVec3dto3f(position), facing, vertexIndex, modelRotationIn, uvLocked);
    }

    public BakedQuad makeBakedQuad(javax.vecmath.Vector3f posFrom, javax.vecmath.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, net.minecraftforge.client.model.ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        return this.makeBakedQuad(TransformVec3f(posFrom), TransformVec3f(posTo), face, sprite, facing, modelRotationIn, partRotation, uvLocked, shade);
    }

    public float[] func_178403_a(javax.vecmath.Vector3f pos1, javax.vecmath.Vector3f pos2) {
        return this.getPositionsDiv16(TransformVec3f(pos1), TransformVec3f(pos2));
    }

    public void func_178404_a(int[] faceData, int storeIndex, int vertexIndex, javax.vecmath.Vector3f pos, int shadeColor, TextureAtlasSprite sprite, BlockFaceUV faceUV) {
        this.storeVertexData(faceData, storeIndex, vertexIndex, TransformVec3f(pos), shadeColor, sprite, faceUV);
    }

    public void func_178406_a(javax.vecmath.Vector3d pos, javax.vecmath.Vector3d rotationOrigin, javax.vecmath.Matrix4d rotationMatrix, javax.vecmath.Vector3d scale) {
        this.rotateScale(TransformVec3dto3f(pos), TransformVec3dto3f(rotationOrigin), TransformMat4dto4f(rotationMatrix), TransformVec3dto3f(scale));
    }

    public void func_178407_a(javax.vecmath.Vector3d p_178407_1_, BlockPartRotation rotation) {
        this.rotatePart(TransformVec3dto3f(p_178407_1_), rotation);
    }

    private javax.vecmath.Matrix4d func_178411_a() {
        javax.vecmath.Matrix4d matrix4d = new javax.vecmath.Matrix4d();
        matrix4d.setIdentity();
        return matrix4d;
    }

    public BakedQuad func_178414_a(javax.vecmath.Vector3f posFrom, javax.vecmath.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ModelRotation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        return this.makeBakedQuad(TransformVec3f(posFrom), TransformVec3f(posTo), face, sprite, facing, modelRotationIn, partRotation, uvLocked, shade);
    }

    public int func_178415_a(javax.vecmath.Vector3d position, EnumFacing facing, int vertexIndex, ModelRotation modelRotationIn, boolean uvLocked) {
        return this.rotateVertex(position, facing, vertexIndex, modelRotationIn, uvLocked);
    }

    public javax.vecmath.Matrix4d func_178416_a(javax.vecmath.AxisAngle4d p_178416_1_) {
        javax.vecmath.Matrix4d matrix4d = func_178411_a();
        matrix4d.setRotation(p_178416_1_);
        return matrix4d;
    }

    @Public
    private static org.lwjgl.util.vector.Vector3f TransformVec3f(javax.vecmath.Vector3f vec) {
        return new org.lwjgl.util.vector.Vector3f(vec.x, vec.y, vec.z);
    }

    @Public
    private static org.lwjgl.util.vector.Vector3f TransformVec3dto3f(javax.vecmath.Vector3d vec) {
        return new org.lwjgl.util.vector.Vector3f((float) vec.x, (float) vec.y, (float) vec.z);
    }

    @Public
    private static org.lwjgl.util.vector.Matrix4f TransformMat4dto4f(javax.vecmath.Matrix4d mat) {
        org.lwjgl.util.vector.Matrix4f lwjglM4f = new org.lwjgl.util.vector.Matrix4f();
        lwjglM4f.m00 = (float)mat.m00;
        lwjglM4f.m01 = (float)mat.m01;
        lwjglM4f.m02 = (float)mat.m02;
        lwjglM4f.m03 = (float)mat.m03;
        lwjglM4f.m10 = (float)mat.m10;
        lwjglM4f.m11 = (float)mat.m11;
        lwjglM4f.m12 = (float)mat.m12;
        lwjglM4f.m13 = (float)mat.m13;
        lwjglM4f.m20 = (float)mat.m20;
        lwjglM4f.m21 = (float)mat.m21;
        lwjglM4f.m22 = (float)mat.m22;
        lwjglM4f.m23 = (float)mat.m23;
        lwjglM4f.m30 = (float)mat.m30;
        lwjglM4f.m31 = (float)mat.m31;
        lwjglM4f.m32 = (float)mat.m32;
        lwjglM4f.m33 = (float)mat.m33;
        return lwjglM4f;
    }
}
