/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.FMLLog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IForgeHooksClient;
import space.libs.interfaces.IVertexFormatElement;
import space.libs.util.cursedmixinextensions.annotations.Public;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4f;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

@SuppressWarnings("unused")
@Mixin(ForgeHooksClient.class)
public class MixinForgeHooksClient implements IForgeHooksClient {

    @Shadow(prefix = "original$")
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
        switch(attrType)
        {
            case POSITION:
                glVertexPointer(element.getElementCount(), element.getType().getGlConstant(), stride, buffer);
                glEnableClientState(GL_VERTEX_ARRAY);
                break;
            case NORMAL:
                if(element.getElementCount() != 3)
                {
                    throw new IllegalArgumentException("Normal attribute should have the size 3: " + element);
                }
                glNormalPointer(element.getType().getGlConstant(), stride, buffer);
                glEnableClientState(GL_NORMAL_ARRAY);
                break;
            case COLOR:
                glColorPointer(element.getElementCount(), element.getType().getGlConstant(), stride, buffer);
                glEnableClientState(GL_COLOR_ARRAY);
                break;
            case UV:
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + element.getIndex());
                glTexCoordPointer(element.getElementCount(), element.getType().getGlConstant(), stride, buffer);
                glEnableClientState(GL_TEXTURE_COORD_ARRAY);
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                break;
            case PADDING:
                break;
            case GENERIC:
                glEnableVertexAttribArray(element.getIndex());
                glVertexAttribPointer(element.getIndex(), element.getElementCount(), element.getType().getGlConstant(), false, stride, buffer);
            default:
                FMLLog.severe("Unimplemented vanilla attribute upload: %s", attrType.getDisplayName());
        }
    }

    @Public
    private static void postDraw(VertexFormatElement.EnumUsage attrType, VertexFormatElement element, int stride, ByteBuffer buffer) {
        switch(attrType)
        {
            case POSITION:
                glDisableClientState(GL_VERTEX_ARRAY);
                break;
            case NORMAL:
                glDisableClientState(GL_NORMAL_ARRAY);
                break;
            case COLOR:
                glDisableClientState(GL_COLOR_ARRAY);
                // is this really needed?
                GlStateManager.resetColor();
                break;
            case UV:
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + element.getIndex());
                glDisableClientState(GL_TEXTURE_COORD_ARRAY);
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                break;
            case PADDING:
                break;
            case GENERIC:
                glDisableVertexAttribArray(element.getIndex());
            default:
                FMLLog.severe("Unimplemented vanilla attribute upload: %s", attrType.getDisplayName());
        }
    }

    @Public
    private static void transform(Vector3d vec, Matrix4f m)
    {
        Vector4f tmp = new Vector4f((float)vec.x, (float)vec.y, (float)vec.z, 1f);
        m.transform(tmp);
        if (Math.abs(tmp.w - 1f) > 1e-5) tmp.scale(1f / tmp.w);
        vec.set(tmp.x, tmp.y, tmp.z);
    }
}
