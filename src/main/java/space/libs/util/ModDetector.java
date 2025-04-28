package space.libs.util;

import codechicken.lib.asm.ModularASMTransformer;
import codechicken.nei.asm.NEITransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.asm.ASMTransformerWrapper;
import space.libs.core.CompatLibCore;
import space.libs.util.mods.ObfCompat;

import java.lang.reflect.Field;
import java.util.*;

public class ModDetector {

    public static boolean initialized = false;

    public static boolean hasLiteLoader = false;

    public static boolean hasSpACore = false;

    public static boolean hasAlexIILLib = false;

    public static boolean hasCivCraft = false;

    public static boolean hasMobends = false;

    public static boolean hasNEI = false;

    public static boolean hasSkybox = false;

    public static ModDetector INSTANCE;

    public ModDetector() {}

    @SuppressWarnings("all")
    public static void init() {
        if (initialized) {
            return;
        }
        INSTANCE = new ModDetector();
        hasLiteLoader = getCoreMod("com.mumfrey.liteloader.launch.LiteLoaderTransformer", false);
        hasNEI = getCoreMod("codechicken.nei.asm.NEICorePlugin", true);
        hasSpACore = getCoreMod("net.specialattack.forge.core.asm.SpACorePlugin", true);
        hasAlexIILLib = getCoreMod("alexiil.mods.lib.coremod.LoadPlugin", true);
        hasCivCraft = getCoreMod("alexiil.mods.civ.coremod.LoadPlugin", true);
        initialized = true;
    }

    @SuppressWarnings("unused")
    public static boolean getCoreMod(String name, boolean init) {
        try {
            Class<?> c = Class.forName(name, init, INSTANCE.getClass().getClassLoader());
        } catch (Exception ignored) {
            CompatLibCore.LOGGER.info("Coremod " + name + " Not Found.");
            return false;
        }
        return true;
    }

    public static boolean mobends() {
        return hasMobends;
    }

    public static boolean skybox() {
        return hasSkybox;
    }

    public static void FixCoremods() {
        if (hasLiteLoader) {
            ObfCompat.init();
        }
        if (hasNEI) {
            try {
                List<IClassTransformer> transformers = Launch.classLoader.getTransformers();
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
                            CompatLibCore.LOGGER.info("Removed unneeded transform from NEI.");
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
