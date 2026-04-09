package space.libs.util;

import alexiil.mods.load.ProgressDisplayer;
import alexiil.mods.load.Translation;
import codechicken.lib.asm.ModularASMTransformer;
import codechicken.nei.asm.NEITransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.ASMTransformerWrapper;
import net.specialattack.forge.core.config.ConfigManager;
import space.libs.core.CompatLibLateCore;
import space.libs.core.ICoreUtils;
import space.libs.util.mods.ObfCompat;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
public class ModDetector implements ICoreUtils {

    public static boolean initialized = false;

    public static boolean hasLiteLoader = false;

    public static boolean hasAllTheItems = false;

    public static boolean hasSpACore = false;

    public static boolean hasAlexIILLib = false;

    public static boolean hasBetterLoadingScreen = false;

    public static boolean hasCivCraft = false;

    public static boolean hasMobends = false;

    public static boolean hasNEI = false;

    public static boolean hasSkybox = false;

    public static boolean hasTC5 = false;

    public static ModDetector INSTANCE;

    public ModDetector() {}

    public static void init() {
        if (initialized) {
            return;
        }
        INSTANCE = new ModDetector();
        hasLiteLoader = getCoreMod("com.mumfrey.liteloader.launch.LiteLoaderTransformer", false);
        hasAllTheItems = getCoreMod("net.fybertech.alltheitems.AllTheItems", true);
        hasNEI = getCoreMod("codechicken.nei.asm.NEICorePlugin", true);
        hasSpACore = getCoreMod("net.specialattack.forge.core.asm.SpACorePlugin", true);
        hasAlexIILLib = getCoreMod("alexiil.mods.lib.coremod.LoadPlugin", true);
        hasBetterLoadingScreen = getCoreMod("alexiil.mods.load.coremod.LoadingScreenLoadPlugin", true);
        hasCivCraft = getCoreMod("alexiil.mods.civ.coremod.LoadPlugin", true);
        initialized = true;
    }

    @SuppressWarnings("unused")
    public static boolean getCoreMod(String name, boolean init) {
        try {
            Class<?> c = findClass(name, init);
        } catch (Exception ignored) {
            LOGGER.info("Coremod " + name + " Not Found.");
            return false;
        }
        return true;
    }

    public static Class<?> findClass(String name, boolean init) throws ClassNotFoundException {
        return Class.forName(name, init, INSTANCE.getClass().getClassLoader());
    }

    public static boolean mobends() {
        return hasMobends;
    }

    public static boolean skybox() {
        return hasSkybox;
    }

    public static ArrayList<String> addEarlyTransformers() {
        ArrayList<String> earlyTransformers = new ArrayList<>();
        if (ModDetector.hasSpACore) {
            LOGGER.info("Found SpACore, load ASM Transformers of it.");
            earlyTransformers.add("net.specialattack.forge.core.asm.SpACoreModTransformer");
            earlyTransformers.add("net.specialattack.forge.core.asm.SpACoreHookTransformer");
            classLoader.addTransformerExclusion("net.specialattack.forge.core.asm");
        }
        return earlyTransformers;
    }

    public static void onInjectData(Map<String, Object> data) {
        File coremodLocation = (File) data.get("coremodLocation");
        CompatLibLateCore.init(coremodLocation);
        if (hasBetterLoadingScreen) {
            try {
                File location = new File(findClass("alexiil.mods.load.coremod.LoadingScreenLoadPlugin", true).getProtectionDomain().getCodeSource().getLocation().toURI());
                LOGGER.info("BetterLoadingScreen Location: " + location);
                Translation.addTranslations(location);
                ProgressDisplayer.start(location);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        if (hasSpACore) {
            if (data.containsKey("mcLocation")) {
                try {
                    ConfigManager.configFolder = new File(((File) data.get("mcLocation")).getCanonicalFile(), "config");
                } catch (IOException e) {
                    throw new IllegalStateException("Failed getting Minecraft config directory", e);
                }
            }
        }
    }

    public static void EarlyLoadTC5() {
        try {
            Class<?> c = Class.forName("thaumcraft.loader.ThaumcraftLoader");
            Method m = c.getDeclaredMethod("load");
            m.invoke(null);
        } catch (ClassNotFoundException not) {
            LOGGER.info("TC5 Not found.");
            return;
        } catch (ReflectiveOperationException ignored) {}
        hasTC5 = true;
    }

    public static void FixCoremods() {
        if (hasLiteLoader) {
            ObfCompat.init();
        }
        if (hasNEI) {
            try {
                List<IClassTransformer> transformers = classLoader.getTransformers();
                for (IClassTransformer transformer : transformers) {
                    if (transformer instanceof ASMTransformerWrapper.TransformerWrapper) {
                        Field f = ASMTransformerWrapper.TransformerWrapper.class.getDeclaredField("parent");
                        f.setAccessible(true);
                        IClassTransformer real = (IClassTransformer) f.get(transformer);
                        if (real instanceof NEITransformer) {
                            NEITransformer nei = (NEITransformer) real;
                            Field f1 = NEITransformer.class.getDeclaredField("transformer");
                            f1.setAccessible(true);
                            ModularASMTransformer asm = (ModularASMTransformer) f1.get(nei);
                            HashMap<String, ModularASMTransformer.ClassNodeTransformerList> map = asm.transformers;
                            Iterator<String> it = map.keySet().iterator();
                            while (it.hasNext()) {
                                String key = it.next();
                                if (key.equals("du") || key.equals("dn")) {
                                    it.remove();
                                    asm.transformers.remove(key);
                                }
                            }
                            LOGGER.info("Removed invalid transforms from NEI.");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
