package bspkrs.bspkrscore.fml;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @apiNote Dummy Class
 */
@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public class BSCClientTicker {

    public static boolean allowUpdateCheck;

    public Minecraft mcClient;

    public static boolean isRegistered;

    public BSCClientTicker() {
        this.mcClient = FMLClientHandler.instance().getClient();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {}

    public static boolean isRegistered() {
        return isRegistered;
    }

    static {
        allowUpdateCheck = false;
        isRegistered = false;
    }
}
