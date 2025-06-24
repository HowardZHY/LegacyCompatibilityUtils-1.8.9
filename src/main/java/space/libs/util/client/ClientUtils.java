package space.libs.util.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public abstract class ClientUtils {

    public static final Minecraft MC = Minecraft.getMinecraft();

    public static VertexFormat POS_TEX_CO_LM_NO = new VertexFormat();

    public static boolean TICK100() {
        return String.valueOf(MC.theWorld.getWorldTime()).endsWith("00");
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
