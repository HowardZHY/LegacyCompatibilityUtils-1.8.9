package space.libs.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("all")
@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    /** scaledTessellator */
    public void func_71392_a(int width, int height, int width2, int height2, int stdTextureWidth, int stdTextureHeight) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
        renderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        renderer.pos((double) (width + 0), (double) (height + stdTextureHeight), 0.0D).tex((double) ((float) (width2 + 0) * var7), (double) ((float) (height2 + stdTextureHeight) * var8)).endVertex();
        renderer.pos((double) (width + stdTextureWidth), (double) (height + stdTextureHeight), 0.0D).tex((double) ((float) (width2 + stdTextureWidth) * var7), (double) ((float) (height2 + stdTextureHeight) * var8)).endVertex();
        renderer.pos((double) (width + stdTextureWidth), (double) (height + 0), 0.0D).tex((double) ((float) (width2 + stdTextureWidth) * var7), (double) ((float) (height2 + 0) * var8)).endVertex();
        renderer.pos((double) (width + 0), (double) (height + 0), 0.0D).tex((double) ((float) (width2 + 0) * var7), (double) ((float) (height2 + 0) * var8)).endVertex();
        Tessellator.getInstance().draw();
    }
}
