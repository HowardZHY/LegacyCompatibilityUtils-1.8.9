package space.libs.util.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IMathUtils {

    default javax.vecmath.Vector3f TransformVec3f(org.lwjgl.util.vector.Vector3f vec) {
        return new javax.vecmath.Vector3f(vec.x, vec.y, vec.z);
    }

    default org.lwjgl.util.vector.Vector3f TransformVec3f(javax.vecmath.Vector3f vec) {
        return new org.lwjgl.util.vector.Vector3f(vec.x, vec.y, vec.z);
    }

    default org.lwjgl.util.vector.Vector3f TransformVec3dTo3f(javax.vecmath.Vector3d vec) {
        return new org.lwjgl.util.vector.Vector3f((float) vec.x, (float) vec.y, (float) vec.z);
    }

    default javax.vecmath.Matrix4d TransformMat4fTo4d(org.lwjgl.util.vector.Matrix4f mat) {
        javax.vecmath.Matrix4d vecM4d = new javax.vecmath.Matrix4d();
        vecM4d.m00 = mat.m00;
        vecM4d.m01 = mat.m01;
        vecM4d.m02 = mat.m02;
        vecM4d.m03 = mat.m03;
        vecM4d.m10 = mat.m10;
        vecM4d.m11 = mat.m11;
        vecM4d.m12 = mat.m12;
        vecM4d.m13 = mat.m13;
        vecM4d.m20 = mat.m20;
        vecM4d.m21 = mat.m21;
        vecM4d.m22 = mat.m22;
        vecM4d.m23 = mat.m23;
        vecM4d.m30 = mat.m30;
        vecM4d.m31 = mat.m31;
        vecM4d.m32 = mat.m32;
        vecM4d.m33 = mat.m33;
        return vecM4d;
    }

    default org.lwjgl.util.vector.Matrix4f TransformMat4f(javax.vecmath.Matrix4f mat) {
        org.lwjgl.util.vector.Matrix4f lwjglM4f = new org.lwjgl.util.vector.Matrix4f();
        lwjglM4f.m00 = mat.m00;
        lwjglM4f.m01 = mat.m01;
        lwjglM4f.m02 = mat.m02;
        lwjglM4f.m03 = mat.m03;
        lwjglM4f.m10 = mat.m10;
        lwjglM4f.m11 = mat.m11;
        lwjglM4f.m12 = mat.m12;
        lwjglM4f.m13 = mat.m13;
        lwjglM4f.m20 = mat.m20;
        lwjglM4f.m21 = mat.m21;
        lwjglM4f.m22 = mat.m22;
        lwjglM4f.m23 = mat.m23;
        lwjglM4f.m30 = mat.m30;
        lwjglM4f.m31 = mat.m31;
        lwjglM4f.m32 = mat.m32;
        lwjglM4f.m33 = mat.m33;
        return lwjglM4f;
    }

    default org.lwjgl.util.vector.Matrix4f TransformMat4dTo4f(javax.vecmath.Matrix4d mat) {
        return TransformMat4f(new javax.vecmath.Matrix4f(mat));
    }
}
