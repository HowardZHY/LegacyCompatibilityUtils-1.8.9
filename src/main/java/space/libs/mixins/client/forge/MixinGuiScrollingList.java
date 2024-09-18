/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.client.GuiScrollingList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(GuiScrollingList.class)
public class MixinGuiScrollingList {

    @Shadow
    private @Final Minecraft client;

    @Shadow
    protected @Final int listWidth;

    @Shadow
    protected @Final int screenWidth;

    @Shadow
    protected @Final int left;

    public void overlayBackground(int top, int height, int alpha1, int alpha2)
    {
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer worldr = tess.getWorldRenderer();
        this.client.renderEngine.bindTexture(Gui.optionsBackground);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float scale = 32.0F;
        double startUV = (screenWidth / scale) / screenWidth * (left);
        worldr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldr.pos(left, height, 0.0D).tex(startUV, height / scale).color(0x40, 0x40, 0x40, alpha2).endVertex();
        worldr.pos(left+listWidth+8, height, 0.0D).tex((left+listWidth+8) / scale, height / scale).color(0x40, 0x40, 0x40, alpha2).endVertex();
        worldr.pos(left+listWidth+8, top,    0.0D).tex((left+listWidth+8) / scale, top / scale   ).color(0x40, 0x40, 0x40, alpha1).endVertex();
        worldr.pos(left, top,    0.0D).tex(startUV, top / scale   ).color(0x40, 0x40, 0x40, alpha1).endVertex();
        tess.draw();
    }
}
