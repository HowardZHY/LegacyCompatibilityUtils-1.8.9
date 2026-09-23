package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ITransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.client.IMathUtils;

import static space.libs.util.client.IMathUtils.*;

@SuppressWarnings("unused")
@Mixin(FaceBakery.class)
public abstract class MixinFaceBakery implements IMathUtils {

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
        return this.rotateVertex(TransformVec3dTo3f(position), facing, vertexIndex, modelRotationIn, uvLocked);
    }

    public BakedQuad makeBakedQuad(javax.vecmath.Vector3f posFrom, javax.vecmath.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, net.minecraftforge.client.model.ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        return this.makeBakedQuad(TransformVector3f(posFrom), TransformVector3f(posTo), face, sprite, facing, modelRotationIn, partRotation, uvLocked, shade);
    }

    public float[] func_178403_a(javax.vecmath.Vector3f pos1, javax.vecmath.Vector3f pos2) {
        return this.getPositionsDiv16(TransformVector3f(pos1), TransformVector3f(pos2));
    }

    public void func_178404_a(int[] faceData, int storeIndex, int vertexIndex, javax.vecmath.Vector3d pos, int shadeColor, TextureAtlasSprite sprite, BlockFaceUV faceUV) {
        this.storeVertexData(faceData, storeIndex, vertexIndex, TransformVec3dTo3f(pos), shadeColor, sprite, faceUV);
    }

    public void func_178406_a(javax.vecmath.Vector3d pos, javax.vecmath.Vector3d rotationOrigin, javax.vecmath.Matrix4d rotationMatrix, javax.vecmath.Vector3d scale) {
        this.rotateScale(TransformVec3dTo3f(pos), TransformVec3dTo3f(rotationOrigin), TransformMat4dTo4f(rotationMatrix), TransformVec3dTo3f(scale));
    }

    public void func_178407_a(javax.vecmath.Vector3d p_178407_1_, BlockPartRotation rotation) {
        this.rotatePart(TransformVec3dTo3f(p_178407_1_), rotation);
    }

    private javax.vecmath.Matrix4d func_178411_a() {
        javax.vecmath.Matrix4d matrix4d = new javax.vecmath.Matrix4d();
        matrix4d.setIdentity();
        return matrix4d;
    }

    public BakedQuad func_178414_a(javax.vecmath.Vector3f posFrom, javax.vecmath.Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ModelRotation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        return this.makeBakedQuad(TransformVector3f(posFrom), TransformVector3f(posTo), face, sprite, facing, modelRotationIn, partRotation, uvLocked, shade);
    }

    public int func_178415_a(javax.vecmath.Vector3d position, EnumFacing facing, int vertexIndex, ModelRotation modelRotationIn, boolean uvLocked) {
        return this.rotateVertex(position, facing, vertexIndex, modelRotationIn, uvLocked);
    }

    public javax.vecmath.Matrix4d func_178416_a(javax.vecmath.AxisAngle4d p_178416_1_) {
        javax.vecmath.Matrix4d matrix4d = func_178411_a();
        matrix4d.setRotation(p_178416_1_);
        return matrix4d;
    }
}
