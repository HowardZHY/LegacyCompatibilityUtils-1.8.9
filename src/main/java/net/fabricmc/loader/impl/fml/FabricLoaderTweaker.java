package net.fabricmc.loader.impl.fml;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.FormattedException;
import net.fabricmc.loader.impl.game.GameProvider;
import net.fabricmc.loader.impl.game.minecraft.launchwrapper.FabricTweaker;
import net.fabricmc.loader.impl.launch.FabricMixinBootstrap;
import net.fabricmc.loader.impl.util.LoaderUtil;
import net.fabricmc.loader.impl.util.SystemProperties;
import net.fabricmc.loader.impl.util.log.Log;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@IFMLLoadingPlugin.SortingIndex(-1001)
public class FabricLoaderTweaker extends FabricTweaker implements IFMLLoadingPlugin {

    private LaunchClassLoader launchclassloader;

    private final List<Path> classpath = new ArrayList<>();

    public FabricLoaderTweaker() {}

    static {
        System.setProperty("fabric.gameJarPath", "./minecraft_server.1.8.9.jar");
        System.setProperty("fabric.gameVersion", "1.8.9");
        System.setProperty("fabric.loader.useCompatibilityClassLoader", "true");
    }

    @Override
    public EnvType getEnvironmentType() {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            return EnvType.CLIENT;
        } else {
            return EnvType.SERVER;
        }
    }

    @Override
    public String getLaunchTarget() {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            return "net.minecraft.client.main.Main";
        } else {
            return "net.minecraft.server.MinecraftServer";
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        Launch.blackboard.put(SystemProperties.DEVELOPMENT, IS_DEVELOPMENT);
        setProperties(Launch.blackboard);
        this.launchclassloader = launchClassLoader;
        launchClassLoader.addClassLoaderExclusion("net.fabricmc.loader");
        launchClassLoader.addClassLoaderExclusion("net.fabricmc.api");
        launchClassLoader.addTransformerExclusion("net.fabricmc.loader.impl");
        try {
            Field f = FabricTweaker.class.getDeclaredField("launchClassLoader");
            f.setAccessible(true);
            f.set(this, launchclassloader);
            init();
        } catch (FormattedException e) {
            handleFormattedException(e);
        } catch (ReflectiveOperationException e2) {
            e2.printStackTrace();
        }
    }

    private void init() {
        setupUncaughtExceptionHandler();
        classpath.clear();
        for (URL url : launchclassloader.getSources()) {
            Path path;
            try {
                path = Paths.get(url.toURI());
            } catch (Exception ignored) {
                System.out.println("Couldn't as path: " + url);
                continue;
            }
            if (!Files.exists(path)) continue;
            classpath.add(LoaderUtil.normalizeExistingPath(path));
        }
        if (arguments == null) {
            super.acceptOptions(new ArrayList<>(), Launch.minecraftHome, Launch.assetsDir, "");
        }
        GameProvider provider = new FMLGameProvider();
        if (!provider.isEnabled() || !provider.locateGame(this, arguments.toArray())) {
            throw new RuntimeException("Could not locate Minecraft: provider locate failed");
        }
        Log.finishBuiltinConfig();
        arguments = null;
        FabricLoaderImpl loader = FabricLoaderImpl.INSTANCE;
        loader.setGameProvider(provider);
        provider.initialize(this);
        loader.load();
        loader.freeze();
        launchclassloader.registerTransformer(FMLFabricClassTransformer.class.getName());
        FabricLoaderImpl.INSTANCE.loadClassTweakers();
        MixinBootstrap.init();
        FabricMixinBootstrap.init(getEnvironmentType(), FabricLoaderImpl.INSTANCE);
        MixinEnvironment.getDefaultEnvironment().setSide(getEnvironmentType() == EnvType.CLIENT ? MixinEnvironment.Side.CLIENT : MixinEnvironment.Side.SERVER);
        Mixins.addConfiguration("mixins.fabricloader.json");
        provider.unlockClassPath(this);
        try {
            loader.invokeEntrypoints("preLaunch", PreLaunchEntrypoint.class, PreLaunchEntrypoint::onPreLaunch);
        } catch (RuntimeException e) {
            throw FormattedException.ofLocalized("exception.initializerFailure", e);
        }
    }

    @Override
    public List<Path> getClassPath() {
        return classpath;
    }

    @Override
    public String[] getASMTransformerClass() {
        injectIntoClassLoader(Launch.classLoader);
        return new String[0];
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
