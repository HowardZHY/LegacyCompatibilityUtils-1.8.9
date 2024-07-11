package space.libs.mixins.client.render;

import net.minecraft.client.renderer.texture.TextureUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

@SuppressWarnings("unused")
@Mixin(TextureUtil.class)
public abstract class MixinTextureUtil {

    @Final
    @Shadow
    private static Logger logger = LogManager.getLogger();

    @Shadow
    static void bindTexture(int p_94277_0_) {}

    @Public
    private static void func_177055_a(String name, int textureId, int mipmapLevels, int width, int height) {
        bindTexture(textureId);
        GL11.glPixelStorei(3333, 1);
        GL11.glPixelStorei(3317, 1);
        for (int i1 = 0; i1 <= mipmapLevels; i1++) {
            File file1 = new File(name + "_" + i1 + ".png");
            int j1 = width >> i1;
            int k1 = height >> i1;
            int l1 = j1 * k1;
            IntBuffer intbuffer = BufferUtils.createIntBuffer(l1);
            int[] aint = new int[l1];
            GL11.glGetTexImage(3553, i1, 32993, 33639, intbuffer);
            intbuffer.get(aint);
            BufferedImage bufferedimage = new BufferedImage(j1, k1, 2);
            bufferedimage.setRGB(0, 0, j1, k1, aint, 0, j1);
            try {
                ImageIO.write(bufferedimage, "png", file1);
                logger.debug("Exported png to: {}", file1.getAbsolutePath());
            } catch (IOException ioexception) {
                logger.debug("Unable to write: ", ioexception);
            }
        }
    }

}
