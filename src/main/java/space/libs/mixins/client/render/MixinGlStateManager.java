package space.libs.mixins.client.render;

import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
@Mixin(GlStateManager.class)
public abstract class MixinGlStateManager {

    @Shadow
    public static void blendFunc(int srcFactor, int dstFactor) {}

    @Shadow
    public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {}

    @Public
    private int func_187397_v(int p_187397_0_) {
        return GL11.glGetInteger(p_187397_0_);
    }

    @Public
    private void func_187398_d(int p_187398_0_) {
        GL14.glBlendEquation(p_187398_0_);
    }

    @Public
    private static void func_187399_a(int p_187399_0_, int p_187399_1_, int p_187399_2_) {
        GL11.glTexEnvi(p_187399_0_, p_187399_1_, p_187399_2_);
    }

    @Public
    private static void func_187400_c(int p_187400_0_, int p_187400_1_, int p_187400_2_, ByteBuffer p_187400_3_) {
        GL11.glColorPointer(p_187400_0_, p_187400_1_, p_187400_2_, p_187400_3_);
    }

    @Public
    private static void func_187401_a(SourceFactor p_187401_0_, DestFactor p_187401_1_) {
        blendFunc(p_187401_0_.field_187395_p, p_187401_1_.field_187345_o);
    }

    @Public
    private static void func_187402_b(int p_187402_0_, FloatBuffer p_187402_1_) {
        GL11.glFog(p_187402_0_, p_187402_1_);
    }

    @Public
    private static void func_187403_b(int p_187403_0_, int p_187403_1_, float p_187403_2_) {
        GL11.glTexParameterf(p_187403_0_, p_187403_1_, p_187403_2_);
    }

    @Public
    private static void func_187404_a(int p_187404_0_, int p_187404_1_, int p_187404_2_, ByteBuffer p_187404_3_) {
        GL11.glTexCoordPointer(p_187404_0_, p_187404_1_, p_187404_2_, p_187404_3_);
    }

    @Public
    private static void func_187405_c(int p_187405_0_, int p_187405_1_, int p_187405_2_, int p_187405_3_) {
        GL11.glTexCoordPointer(p_187405_0_, p_187405_1_, p_187405_2_, p_187405_3_);
    }

    @Public
    private static void func_187406_e(int p_187406_0_, int p_187406_1_, int p_187406_2_, int p_187406_3_) {
        GL11.glColorPointer(p_187406_0_, p_187406_1_, p_187406_2_, p_187406_3_);
    }

    @Public
    private static void func_187409_d(int p_187409_0_, int p_187409_1_) {
        GL11.glPolygonMode(p_187409_0_, p_187409_1_);
    }

    @Public
    private static void func_187410_q(int p_187410_0_) {
        GL11.glEnableClientState(p_187410_0_);
    }

    @Public
    private static int func_187411_c(int p_187411_0_, int p_187411_1_, int p_187411_2_) {
        return GL11.glGetTexLevelParameteri(p_187411_0_, p_187411_1_, p_187411_2_);
    }

    @Public
    private static void func_187412_c(int p_187412_0_, int p_187412_1_) {
        GL11.glFogi(p_187412_0_, p_187412_1_);
    }

    @Public
    private static void func_187415_K() {
        GL11.glEndList();
    }

    @Public
    private static void func_187420_d(int p_187420_0_, int p_187420_1_, int p_187420_2_, int p_187420_3_) {
        GL11.glVertexPointer(p_187420_0_, p_187420_1_, p_187420_2_, p_187420_3_);
    }

    @Public
    private static void func_187421_b(int p_187421_0_, int p_187421_1_, int p_187421_2_) {
        GL11.glTexParameteri(p_187421_0_, p_187421_1_, p_187421_2_);
    }

    @Public
    private static void func_187423_f(int p_187423_0_, int p_187423_1_) {
        GL11.glNewList(p_187423_0_, p_187423_1_);
    }

    @Public
    private static void func_187425_g(int p_187425_0_, int p_187425_1_) {
        GL11.glPixelStorei(p_187425_0_, p_187425_1_);
    }

    @Public
    private static void func_187426_b(float p_187426_0_, float p_187426_1_) {
        GL11.glTexCoord2f(p_187426_0_, p_187426_1_);
    }

    @Public
    private static void func_187428_a(SourceFactor p_187428_0_, DestFactor p_187428_1_, SourceFactor p_187428_2_, DestFactor p_187428_3_) {
        tryBlendFuncSeparate(p_187428_0_.field_187395_p, p_187428_1_.field_187345_o, p_187428_2_.field_187395_p, p_187428_3_.field_187345_o);
    }

    @Public
    private static void func_187429_p(int p_187429_0_) {
        GL11.glDisableClientState(p_187429_0_);
    }

    @Public
    private static void func_187432_a(float p_187432_0_, float p_187432_1_, float p_187432_2_) {
        GL11.glNormal3f(p_187432_0_, p_187432_1_, p_187432_2_);
    }

    @Public
    private static int func_187434_L() {
        return GL11.glGetError();
    }

    @Public
    private static void func_187435_e(float p_187435_0_, float p_187435_1_, float p_187435_2_) {
        GL11.glVertex3f(p_187435_0_, p_187435_1_, p_187435_2_);
    }

    @Public
    private static void func_187436_a(int p_187436_0_, int p_187436_1_, float p_187436_2_) {
        GL11.glTexEnvf(p_187436_0_, p_187436_1_, p_187436_2_);
    }

    @Public
    private static void func_187437_J() {
        GL11.glEnd();
    }

    @Public
    private static void func_187439_f(int p_187439_0_, int p_187439_1_, int p_187439_2_) {
        GL11.glDrawArrays(p_187439_0_, p_187439_1_, p_187439_2_);
    }

    @Public
    private static void func_187441_d(float p_187441_0_) {
        GL11.glLineWidth(p_187441_0_);
    }

    @Public
    private static int func_187442_t(int p_187442_0_) {
        return GL11.glGenLists(p_187442_0_);
    }

    @Public
    private static void func_187443_a(int p_187443_0_, int p_187443_1_, int p_187443_2_, int p_187443_3_, int p_187443_4_, int p_187443_5_, int p_187443_6_, int p_187443_7_) {
        GL11.glCopyTexSubImage2D(p_187443_0_, p_187443_1_, p_187443_2_, p_187443_3_, p_187443_4_, p_187443_5_, p_187443_6_, p_187443_7_);
    }

    @Public
    private static void func_187447_r(int p_187447_0_) {
        GL11.glBegin(p_187447_0_);
    }

    @Public
    private static void func_187449_e(int p_187449_0_, int p_187449_1_) {
        GL11.glDeleteLists(p_187449_0_, p_187449_1_);
    }
}
