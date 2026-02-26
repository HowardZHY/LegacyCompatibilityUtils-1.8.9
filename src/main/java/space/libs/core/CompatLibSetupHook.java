package space.libs.core;

import net.minecraftforge.fml.relauncher.FMLInjectionData;
import net.minecraftforge.fml.relauncher.IFMLCallHook;
import space.libs.util.ModDetector;

import java.util.Map;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class CompatLibSetupHook implements IFMLCallHook {

    @Override
    public void injectData(Map<String, Object> data) {
        ModDetector.EarlyLoadTC5();
    }

    @Override
    public Void call() throws Exception {
        CompatLibCore.LOGGER.info("Mods calling setup hooks...");
        if (ModDetector.hasAllTheItems) {
            addModContainer("net.fybertech.alltheitems.DummyMod");
        }
        if (CompatLibCoreConfig.DummyModID) {
            addDummyMods();
        }
        return null;
    }

    public static void addDummyMods() {
        addModContainer("bspkrs.bspkrscore.fml.BspkrsCoreDummyContainer");
        addModContainer("com.superdextor.thinkbigcore.ThinkBigCoreDummyContainer");
    }

    public static void addModContainer(String container) {
        FMLInjectionData.containers.add(container);
    }
}
