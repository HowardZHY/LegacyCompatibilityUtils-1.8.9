package space.libs.mixins.client.render;

import net.minecraft.client.renderer.GlStateManager;

import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
@Mixin(GlStateManager.class)
public class MixinGlStateManager {

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
    private static void func_187423_f(int p_187423_0_, int p_187423_1_) {
        GL11.glNewList(p_187423_0_, p_187423_1_);
    }

}
