package net.fabricmc.loader.impl.fml;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.ObjectShare;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.ModDependency;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.FormattedException;
import net.fabricmc.loader.impl.game.GameProviderHelper;
import net.fabricmc.loader.impl.game.LibClassifier;
import net.fabricmc.loader.impl.game.minecraft.*;
import net.fabricmc.loader.impl.game.patch.GameTransformer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.launch.MappingConfiguration;
import net.fabricmc.loader.impl.metadata.BuiltinModMetadata;
import net.fabricmc.loader.impl.metadata.ModDependencyImpl;
import net.fabricmc.loader.impl.util.*;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FMLGameProvider extends MinecraftGameProvider {

    private static final String[] ALLOWED_EARLY_CLASS_PREFIXES = { "org.apache.logging.log4j.", "com.mojang.util." };

    private static final Set<String> SENSITIVE_ARGS = new HashSet<>(Arrays.asList(
        "accesstoken",
        "clientid",
        "profileproperties",
        "proxypass",
        "proxyuser",
        "username",
        "userproperties",
        "uuid",
        "xuid"));

    private EnvType envType;
    private String entrypoint;
    private Arguments arguments;
    private final List<Path> gameJars = new ArrayList<>(2); // env game jar and potentially common game jar
    private Path realmsJar;
    private final Set<Path> logJars = new HashSet<>();
    private boolean log4jAvailable;
    private boolean slf4jAvailable;
    private final List<Path> miscGameLibraries = new ArrayList<>(); // libraries not relevant for loader's uses
    private Collection<Path> validParentClassPath; // computed parent class path restriction (loader+deps)
    private McVersion versionData;
    private final GameTransformer transformer = new GameTransformer();

    @Override
    public String getGameId() {
        return super.getGameId();
    }

    @Override
    public String getGameName() {
        return super.getGameName();
    }

    @Override
    public String getRawGameVersion() {
        return versionData.getRaw();
    }

    @Override
    public String getNormalizedGameVersion() {
        return versionData.getNormalized();
    }

    @Override
    public Collection<BuiltinMod> getBuiltinMods() {
        BuiltinModMetadata.Builder metadata = new BuiltinModMetadata.Builder(getGameId(), getNormalizedGameVersion()).setName(getGameName());
        if (versionData.getClassVersion().isPresent()) {
            int version = versionData.getClassVersion().getAsInt() - 44;
            try {
                metadata.addDependency(new ModDependencyImpl(ModDependency.Kind.DEPENDS, "java", Collections.singletonList(String.format(Locale.ENGLISH, ">=%d", version))));
            } catch (VersionParsingException e) {
                throw new RuntimeException(e);
            }
        }
        return Collections.singletonList(new BuiltinMod(gameJars, metadata.build()));
    }

    public Path getGameJar() {
        return gameJars.get(0);
    }

    @Override
    public String getEntrypoint() {
        return entrypoint;
    }

    @Override
    public Path getLaunchDirectory() {
        if (arguments == null) {
            return Paths.get(".");
        }

        return getLaunchDirectory(arguments);
    }

    @Override
    public boolean requiresUrlClassLoader() {
        return false;
    }

    @Override
    public Set<BuiltinTransform> getBuiltinTransforms(String className) {
        boolean isMinecraftClass = className.startsWith("net.minecraft.")
            || className.startsWith("com.mojang.blaze3d.")
            || className.indexOf('.') < 0; // obf classes
        if (isMinecraftClass) {
            if (FabricLoaderImpl.INSTANCE.isDevelopmentEnvironment()) {
                return TRANSFORM_WIDENALL_STRIPENV_CLASSTWEAKS;
            } else {
                return TRANSFORM_WIDENALL_CLASSTWEAKS;
            }
        } else {
            return TRANSFORM_STRIPENV;
        }
    }

    private static final Set<BuiltinTransform> TRANSFORM_WIDENALL_STRIPENV_CLASSTWEAKS = EnumSet.of(BuiltinTransform.WIDEN_ALL_PACKAGE_ACCESS, BuiltinTransform.STRIP_ENVIRONMENT, BuiltinTransform.CLASS_TWEAKS);
    private static final Set<BuiltinTransform> TRANSFORM_WIDENALL_CLASSTWEAKS = EnumSet.of(BuiltinTransform.WIDEN_ALL_PACKAGE_ACCESS, BuiltinTransform.CLASS_TWEAKS);
    private static final Set<BuiltinTransform> TRANSFORM_STRIPENV = EnumSet.of(BuiltinTransform.STRIP_ENVIRONMENT);

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public boolean locateGame(FabricLauncher launcher, String[] args) {
        this.envType = launcher.getEnvironmentType();
        this.arguments = new Arguments();
        arguments.parse(args);
        try {
            LibClassifier<MCLibrary> classifier = new LibClassifier<>(MCLibrary.class, envType, this);
            MCLibrary envGameLib = envType == EnvType.CLIENT ? MCLibrary.MC_CLIENT : MCLibrary.MC_SERVER;
            Path commonGameJar = GameProviderHelper.getCommonGameJar();
            Path envGameJar = GameProviderHelper.getEnvGameJar(envType);
            boolean commonGameJarDeclared = commonGameJar != null;
            if (commonGameJarDeclared) {
                if (envGameJar != null) {
                    classifier.process(envGameJar, MCLibrary.MC_COMMON);
                }
                classifier.process(commonGameJar);
            } else if (envGameJar != null) {
                classifier.process(envGameJar);
            }
            classifier.process(launcher.getClassPath());
            envGameJar = classifier.getOrigin(envGameLib);
            if (envGameJar == null) return false;
            commonGameJar = classifier.getOrigin(MCLibrary.MC_COMMON);
            if (commonGameJarDeclared && commonGameJar == null) {
                Log.warn(LogCategory.GAME_PROVIDER, "The declared common game jar didn't contain any of the expected classes!");
            }
            gameJars.add(envGameJar);
            if (commonGameJar != null && !commonGameJar.equals(envGameJar)) {
                gameJars.add(commonGameJar);
            }
            Path assetsJar = classifier.getOrigin(MCLibrary.MC_ASSETS_ROOT);
            if (assetsJar != null && !assetsJar.equals(commonGameJar) && !assetsJar.equals(envGameJar)) {
                gameJars.add(assetsJar);
            }
            entrypoint = classifier.getClassName(envGameLib);
            realmsJar = classifier.getOrigin(MCLibrary.REALMS);
            log4jAvailable = classifier.has(MCLibrary.LOG4J_API) && classifier.has(MCLibrary.LOG4J_CORE);
            slf4jAvailable = classifier.has(MCLibrary.SLF4J_API) && classifier.has(MCLibrary.SLF4J_CORE);
            boolean hasLogLib = log4jAvailable || slf4jAvailable;
            Log.configureBuiltin(hasLogLib, !hasLogLib);
            for (MCLibrary lib : MCLibrary.LOGGING) {
                Path path = classifier.getOrigin(lib);
                if (path != null) {
                    if (hasLogLib) {
                        logJars.add(path);
                    } else if (!gameJars.contains(path)) {
                        miscGameLibraries.add(path);
                    }
                }
            }
            miscGameLibraries.addAll(classifier.getUnmatchedOrigins());
            validParentClassPath = classifier.getSystemLibraries();
        } catch (IOException e) {
            throw ExceptionUtil.wrap(e);
        }
        ObjectShare share = FabricLoaderImpl.INSTANCE.getObjectShare();
        share.put("fabric-loader:inputGameJar", gameJars.get(0));
        share.put("fabric-loader:inputGameJars", Collections.unmodifiableList(new ArrayList<>(gameJars)));
        if (realmsJar != null) share.put("fabric-loader:inputRealmsJar", realmsJar);
        String version = arguments.remove(Arguments.GAME_VERSION);
        if (version == null) version = System.getProperty(SystemProperties.GAME_VERSION);
        versionData = McVersionLookup.getVersion(gameJars, entrypoint, version);
        processArgumentMap(arguments, envType);
        return true;
    }

    private static void processArgumentMap(Arguments argMap, EnvType envType) {
        switch (envType) {
            case CLIENT:
                if (!argMap.containsKey("accessToken")) {
                    argMap.put("accessToken", "FabricMC");
                }
                if (!argMap.containsKey("version")) {
                    argMap.put("version", "Fabric");
                }
                String versionType = "";
                if (argMap.containsKey("versionType") && !argMap.get("versionType").equalsIgnoreCase("release")) {
                    versionType = argMap.get("versionType") + "/";
                }
                argMap.put("versionType", versionType + "Fabric");
                if (!argMap.containsKey("gameDir")) {
                    argMap.put("gameDir", getLaunchDirectory(argMap).toAbsolutePath().normalize().toString());
                }
                break;
            case SERVER:
                argMap.remove("version");
                argMap.remove("gameDir");
                argMap.remove("assetsDir");
                break;
        }
    }

    private static Path getLaunchDirectory(Arguments argMap) {
        return Paths.get(argMap.getOrDefault("gameDir", "."));
    }

    @Override
    public void initialize(FabricLauncher launcher) {
        launcher.setValidParentClassPath(validParentClassPath);
        MappingConfiguration config = launcher.getMappingConfiguration();
        String runtimeNs = config.getRuntimeNamespace();
        String gameNs = System.getProperty(SystemProperties.GAME_MAPPING_NAMESPACE);
        if (gameNs == null) {
            gameNs = MappingConfiguration.OFFICIAL_NAMESPACE;
            if (config.hasAnyMappings()) {
                List<String> mappingNamespaces = config.getNamespaces();
                if (mappingNamespaces != null) {
                    if (launcher.isDevelopment()
                        && mappingNamespaces.contains(MappingConfiguration.NAMED_NAMESPACE)) { // dev with named (e.g. yarn)
                        gameNs = MappingConfiguration.NAMED_NAMESPACE;
                    } else if (!mappingNamespaces.contains(MappingConfiguration.OFFICIAL_NAMESPACE)) { // prod with old mc that didn't use the same mappings for client and server jars
                        gameNs = envType == EnvType.CLIENT ? MappingConfiguration.CLIENT_OFFICIAL_NAMESPACE : MappingConfiguration.SERVER_OFFICIAL_NAMESPACE;
                    }
                }
            }
        }
        Log.debug(LogCategory.GAME_PROVIDER, "namespace detection result: game=%s runtime=%s mod-default=%s", gameNs, runtimeNs, config.getDefaultModDistributionNamespace());
        if (!gameNs.equals(runtimeNs)) { // game is obfuscated / in another namespace -> remap
            Map<String, Path> obfJars = new HashMap<>(3);
            String[] names = new String[gameJars.size()];
            for (int i = 0; i < gameJars.size(); i++) {
                String name;
                if (i == 0) {
                    name = envType.name().toLowerCase(Locale.ENGLISH);
                } else if (i == 1) {
                    name = "common";
                } else {
                    name = String.format(Locale.ENGLISH, "extra-%d", i - 2);
                }
                obfJars.put(name, gameJars.get(i));
                names[i] = name;
            }
            if (realmsJar != null) {
                obfJars.put("realms", realmsJar);
            }
            obfJars = GameProviderHelper.deobfuscate(obfJars,
                gameNs,
                getGameId(), getNormalizedGameVersion(),
                getLaunchDirectory(),
                launcher);
            for (int i = 0; i < gameJars.size(); i++) {
                Path newJar = obfJars.get(names[i]);
                Path oldJar = gameJars.set(i, newJar);

                if (logJars.remove(oldJar)) logJars.add(newJar);
            }
            realmsJar = obfJars.get("realms");
        }
        // Load the logger libraries on the platform CL when in a unit test
        if (!logJars.isEmpty() && !Boolean.getBoolean(SystemProperties.UNIT_TEST)) {
            for (Path jar : logJars) {
                if (gameJars.contains(jar)) {
                    launcher.addToClassPath(jar, ALLOWED_EARLY_CLASS_PREFIXES);
                } else {
                    launcher.addToClassPath(jar);
                }
            }
        }
        setupLogHandler(launcher);
        this.transformer.locateEntrypoints(launcher, gameJars);
    }

    private void setupLogHandler(FabricLauncher launcher) {
        System.setProperty("log4j2.formatMsgNoLookups", "true");
        ClassLoader prevCl = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(launcher.getTargetClassLoader());
        if (log4jAvailable) {
            Log.init(new Log4jLogHandler());
        } else if (slf4jAvailable) {
            Log.init(new Slf4jLogHandler());
        } else {
            return;
        }
        Thread.currentThread().setContextClassLoader(prevCl);
    }

    @Override
    public Arguments getArguments() {
        return arguments;
    }

    @Override
    public String[] getLaunchArguments(boolean sanitize) {
        if (arguments == null) return new String[0];
        String[] ret = arguments.toArray();
        if (!sanitize) return ret;
        int writeIdx = 0;
        for (int i = 0; i < ret.length; i++) {
            String arg = ret[i];
            if (i + 1 < ret.length
                && arg.startsWith("--")
                && SENSITIVE_ARGS.contains(arg.substring(2).toLowerCase(Locale.ENGLISH))) {
                i++; // skip value
            } else {
                ret[writeIdx++] = arg;
            }
        }
        if (writeIdx < ret.length) ret = Arrays.copyOf(ret, writeIdx);
        return ret;
    }

    @Override
    public GameTransformer getEntrypointTransformer() {
        return this.transformer;
    }

    @Override
    public boolean canOpenErrorGui() {
        if (arguments == null || envType == EnvType.CLIENT) {
            return true;
        }
        List<String> extras = arguments.getExtraArgs();
        return !extras.contains("nogui") && !extras.contains("--nogui");
    }

    @Override
    public boolean hasAwtSupport() {
        return super.hasAwtSupport();
    }

    @Override
    public void unlockClassPath(FabricLauncher launcher) {
        for (Path gameJar : gameJars) {
            if (logJars.contains(gameJar)) {
                launcher.setAllowedPrefixes(gameJar);
            } else {
                launcher.addToClassPath(gameJar);
            }
        }

        if (realmsJar != null) launcher.addToClassPath(realmsJar);

        for (Path lib : miscGameLibraries) {
            launcher.addToClassPath(lib);
        }
    }

    @SuppressWarnings("ConfusingArgumentToVarargsMethod")
    @Override
    public void launch(ClassLoader loader) {
        String targetClass = entrypoint;
        if (envType == EnvType.CLIENT && targetClass.contains("Applet")) {
            targetClass = "net.fabricmc.loader.impl.game.minecraft.applet.AppletMain";
        }
        MethodHandle invoker;
        try {
            Class<?> c = loader.loadClass(targetClass);
            invoker = MethodHandles.lookup().findStatic(c, "main", MethodType.methodType(void.class, String[].class));
        } catch (NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw FormattedException.ofLocalized("exception.minecraft.invokeFailure", e);
        }
        try {
            invoker.invokeExact(arguments.toArray());
        } catch (Throwable t) {
            throw FormattedException.ofLocalized("exception.minecraft.generic", t);
        }
    }
}

