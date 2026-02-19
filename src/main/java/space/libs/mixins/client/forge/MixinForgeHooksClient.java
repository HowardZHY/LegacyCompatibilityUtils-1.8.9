/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.FMLLog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.interfaces.IForgeHooksClient;
import space.libs.interfaces.IVertexFormatElement;
import space.libs.util.client.ClientUtils;
import space.libs.util.cursedmixinextensions.annotations.Public;

import javax.vecmath.*;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

@SuppressWarnings("all")
@Mixin(value = ForgeHooksClient.class, priority = 4000)
public class MixinForgeHooksClient implements IForgeHooksClient {

    @Public
    private static EnumWorldBlockLayer renderLayer = EnumWorldBlockLayer.SOLID;

    @Inject(method = "onTextureStitchedPre", at = @At("RETURN"), remap = false)
    private static void onTextureStitchedPre(TextureMap map, CallbackInfo ci) {
        ClientUtils.onTextureStitchedPre(map);
    }

    @Shadow(prefix = "original$", remap = false)
    public static ModelBiped original$getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int slotID, ModelBiped _default) {
        throw new AbstractMethodError();
    }

    @Public
    private static ModelBase getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int slotID, ModelBase baseModel) {
        return original$getArmorModel(entityLiving, itemStack, slotID, (ModelBiped) baseModel);
    }

    @Public
    private static void preDraw(VertexFormatElement.EnumUsage attrType, VertexFormatElement element, int stride, ByteBuffer buffer) {
        IVertexFormatElement accessor = (IVertexFormatElement) element;
        buffer.position(accessor.func_177373_a());
        final int o = attrType.ordinal();
        if (o == 0) {
            glVertexPointer(element.getElementCount(), element.getType().getGlConstant(), stride, buffer);
            glEnableClientState(GL_VERTEX_ARRAY);
        } else if (o == 1) {
            if(element.getElementCount() != 3) {
                throw new IllegalArgumentException("Normal attribute should have the size 3: " + element);
            }
            glNormalPointer(element.getType().getGlConstant(), stride, buffer);
            glEnableClientState(GL_NORMAL_ARRAY);
        } else if (o == 2) {
            glColorPointer(element.getElementCount(), element.getType().getGlConstant(), stride, buffer);
            glEnableClientState(GL_COLOR_ARRAY);
        } else if (o == 3) {
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + element.getIndex());
            glTexCoordPointer(element.getElementCount(), element.getType().getGlConstant(), stride, buffer);
            glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
        } else if (o == 6) {
            return;
        } else if (o == 7) {
            glEnableVertexAttribArray(element.getIndex());
            glVertexAttribPointer(element.getIndex(), element.getElementCount(), element.getType().getGlConstant(), false, stride, buffer);
        } else {
            FMLLog.severe("Unimplemented vanilla attribute upload: " + attrType.getDisplayName());
        }
    }

    @Public
    private static void postDraw(VertexFormatElement.EnumUsage attrType, VertexFormatElement element, int stride, ByteBuffer buffer) {
        final int o = attrType.ordinal();
        if (o == 0) {
            glDisableClientState(GL_VERTEX_ARRAY);
        } else if (o == 1) {
            glDisableClientState(GL_NORMAL_ARRAY);
        } else if (o == 2) {
            glDisableClientState(GL_COLOR_ARRAY);
            // is this really needed?
            GlStateManager.resetColor();
        } else if (o == 3) {
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + element.getIndex());
            glDisableClientState(GL_TEXTURE_COORD_ARRAY);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
        } else if (o == 6) {
            return;
        } else if (o == 7) {
            glDisableVertexAttribArray(element.getIndex());
        } else {
            FMLLog.severe("Unimplemented vanilla attribute upload: " + attrType.getDisplayName());
        }
    }

    @Public
    private static void transformV3d(Vector3d vec, Matrix4f m) {
        Vector4f tmp = new Vector4f((float)vec.x, (float)vec.y, (float)vec.z, 1f);
        m.transform(tmp);
        if (Math.abs(tmp.w - 1f) > 1e-5) tmp.scale(1f / tmp.w);
        vec.set(tmp.x, tmp.y, tmp.z);
    }
}
