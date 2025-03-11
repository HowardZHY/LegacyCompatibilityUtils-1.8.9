package space.libs;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;
import space.libs.event.GuiScreenEventHandler;
import space.libs.util.ModDetector;
import space.libs.util.mods.SkyboxEventHandler;

@SuppressWarnings("all")
@Mod(
    modid = "compatlib",
    version = "1.8.9",
    name = "CompatLib",
    acceptedMinecraftVersions = "*",
    acceptableRemoteVersions = "*"
)
public class CompatLib {

    public static final String MODID = "compatlib";

    public static final Logger LOGGER = FMLLog.getLogger();

    public CompatLib() {
        LOGGER.info("[CompatLib] Loading...");
    }

    public static String getMCVersion() {
        return Loader.instance().getMCVersionString();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            if (ModDetector.mobends()) {
                FMLCommonHandler.instance().bus().register(new GuiScreenEventHandler());
            }
            if (ModDetector.skybox()) {
                FMLCommonHandler.instance().bus().register(new SkyboxEventHandler());
            }
        }
    }
}
