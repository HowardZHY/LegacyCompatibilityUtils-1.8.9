package net.legacyfabric.loader.impl.fml.core;

import net.fabricmc.api.EnvType;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

public class LoadingUtils {

    public static boolean LEGACY = false;

    public static EnvType ENVIRONMENT;

    public static String VERSION;

    public static void init() {
        try {
            Class<?> ignored = Class.forName("net.minecraftforge.fml.relauncher.FMLLaunchHandler");
        } catch (Exception ignored) {
            LEGACY = true;
            System.out.println("Loading on 1.7.10 or before...");
        }
        ENVIRONMENT = getEnvironment();
        VERSION = getGameVersion();
        System.setProperty("fabric.gameJarPath", getJarPath());
        System.setProperty("fabric.gameVersion", VERSION);
        System.setProperty("fabric.loader.useCompatibilityClassLoader", "true");
    }

    public static EnvType getEnvironment() {
        if (LEGACY) {
            if (cpw.mods.fml.relauncher.FMLLaunchHandler.side() == cpw.mods.fml.relauncher.Side.CLIENT) {
                return EnvType.CLIENT;
            } else {
                return EnvType.SERVER;
            }
        } else {
            if (net.minecraftforge.fml.relauncher.FMLLaunchHandler.side() == net.minecraftforge.fml.relauncher.Side.CLIENT) {
                return EnvType.CLIENT;
            } else {
                return EnvType.SERVER;
            }
        }
    }

    public static String getGameVersion() {
        if (LEGACY) {
            return cpw.mods.fml.relauncher.FMLInjectionData.data()[4].toString();
        } else {
            return net.minecraftforge.fml.relauncher.FMLInjectionData.data()[4].toString();
        }
    }

    public static String getJarPath() {
        try {
            return LoadingUtils.getGameJarPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getGameJarPath() throws IOException {
        Path propertiesFile = Paths.get("fabric-loader-mod.properties");
        Properties properties = new Properties();
        if (Files.exists(propertiesFile)) {
            try (Reader reader = Files.newBufferedReader(propertiesFile)) {
                properties.load(reader);
            }
        }
        if (!properties.containsKey("gameJar")) {
            if (ENVIRONMENT == EnvType.SERVER) {
                properties.put("gameJar", replaceVersion("minecraft_server.%.jar"));
            } else {
                Path parent = propertiesFile.getParent();
                if (parent.getParent().getFileName().endsWith("versions")) {
                    properties.put("gameJar", parent.getFileName() + ".jar");
                } else {
                    properties.put("gameJar", ".jar"); // TODO: couldn't get?
                }
            }
            try (Writer writer = Files.newBufferedWriter(propertiesFile)) {
                properties.store(writer, null);
            }
        }
        return (String) properties.get("gameJar");
    }

    public static String replaceVersion(String in) {
        return in.replace("%", VERSION);
    }
}
