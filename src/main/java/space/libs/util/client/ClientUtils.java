package space.libs.util.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public abstract class ClientUtils {

    public static final Minecraft MC = Minecraft.getMinecraft();

    public static boolean TICK100() {
        return String.valueOf(MC.theWorld.getWorldTime()).endsWith("00");
    }
}
