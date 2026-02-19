package space.libs.util.client;

import com.google.common.collect.BiMap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import space.libs.interfaces.IFluid;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public abstract class ClientUtils {

    public static final Minecraft MC = Minecraft.getMinecraft();

    public static VertexFormat POS_TEX_CO_LM_NO = new VertexFormat();

    public static boolean TICK100() {
        return String.valueOf(MC.theWorld.getWorldTime()).endsWith("00");
    }

    public static void onTextureStitchedPre(TextureMap map) {
        BiMap<String, Fluid> fluids = ReflectionHelper.getPrivateValue(FluidRegistry.class, null, "fluids");
        for (Fluid fluid : fluids.values()) {
            IFluid accessor = (IFluid) fluid;
            if (fluid.getStill() != null) {
                TextureAtlasSprite still = map.registerSprite(fluid.getStill());
                accessor.setStillIcon(still);
            }
            if (fluid.getFlowing() != null) {
                TextureAtlasSprite flowing = map.registerSprite(fluid.getFlowing());
                accessor.setStillIcon(flowing);
            }
        }
    }

    static {
        POS_TEX_CO_LM_NO
            .addElement(DefaultVertexFormats.POSITION_3F)
            .addElement(DefaultVertexFormats.TEX_2F)
            .addElement(DefaultVertexFormats.COLOR_4UB)
            .addElement(DefaultVertexFormats.TEX_2S)
            .addElement(DefaultVertexFormats.NORMAL_3B)
            .addElement(DefaultVertexFormats.PADDING_1B);
    }
}
