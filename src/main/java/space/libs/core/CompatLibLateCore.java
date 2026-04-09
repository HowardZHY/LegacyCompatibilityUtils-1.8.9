package space.libs.core;

import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import space.libs.util.ModDetector;

import java.io.File;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Map;

@IFMLLoadingPlugin.SortingIndex(1001)
public class CompatLibLateCore extends ModDetector implements IFMLLoadingPlugin {

    public static void init(File location) {
        try {
            Method load = CoreModManager.class.getDeclaredMethod("loadCoreMod", LaunchClassLoader.class, String.class, File.class);
            load.setAccessible(true);
            load.invoke(null, classLoader, "space.libs.core.CompatLibLateCore", location);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        ArrayList<String> lateTransformers = new ArrayList<>();
        if (hasAlexIILLib) {
            LOGGER.info("Found AlexIILLib, load ASM Transformers of it.");
            lateTransformers.add("alexiil.mods.lib.coremod.ClassTransformer");
            classLoader.addTransformerExclusion("alexiil.mods.lib.coremod");
            if (hasBetterLoadingScreen) {
                LOGGER.info("Found BetterLoadingScreen, load ASM Transformers of it.");
                lateTransformers.add("alexiil.mods.load.coremod.BetterLoadingScreenTransformer");
            }
            if (hasCivCraft) {
                LOGGER.info("Found CivCraft, load ASM Transformers of it.");
                lateTransformers.add("alexiil.mods.civ.coremod.CivCraftTransformer");
            }
        }
        String[] transformers = new String[lateTransformers.size()];
        return lateTransformers.toArray(transformers);
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
