package space.libs;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;

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
    public void init(FMLInitializationEvent event) {}
}
