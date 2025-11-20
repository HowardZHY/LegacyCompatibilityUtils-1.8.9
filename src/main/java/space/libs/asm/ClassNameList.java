package space.libs.asm;

import java.util.Arrays;

public class ClassNameList {

    public static boolean StartsWith(String name) {
        return Arrays.stream(ClassNameList.WHITELIST).anyMatch(name::startsWith);
    }

    public static boolean StartsWithBlacklist(String name) {
        return Arrays.stream(ClassNameList.BLACKLIST).anyMatch(name::startsWith);
    }

    public static boolean Contains(String name) {
        return Arrays.stream(ClassNameList.CONTAINS_WHITELIST).anyMatch(name::contains);
    }

    public static boolean ContainsBlacklist(String name) {
        return Arrays.stream(ClassNameList.CONTAINS_BLACKLIST).anyMatch(name::startsWith);
    }

    /** Things shouldn't be transformed */
    public static String[] WHITELIST = {
        "barit",
        "cc.polyfr",
        "ch.qos.log",
        "club.sk1er.patch",
        "co.skycli",
        "com.ibm.",
        "com.jacob",
        "com.jcraft",
        "com.labymed",
        "com.llamalad",
        "com.mojang",
        "com.mumfrey.lite",
        "com.goog",
        "com.gtnewh",
        "com.replaymod",
        "com.shadowhawk",
        "com.sun",
        "com.thevoxelbox.voxelm",
        "com.typesafe",
        "com.viaver",
        "com.unascribed.ear",
        "com.xuggle",
        "customskin",
        "de.florianmich",
        "dev.tr7",
        "eu.the5z",
        "gg.essen",
        "gg.skytil",
        "gnu.trove",
        "groovy",
        "io.github.legacymod",
        "io.github.moulberry",
        "io.netty",
        "it.unimi",
        "java",
        "jopt",
        "junit",
        "me.jellysquid.mods.ph",
        "moe.nea",
        "net.ccbluex",
        "net.java.",
        "net.jpointz",
        "net.labymod",
        "net.md_5.spec",
        "net.minecraft",
        "net.minecraftforge.fml",
        "net.raphimc",
        "net.weavemc",
        "oshi",
        "org.apache",
        "org.h2",
        "org.intellij",
        "org.jetbrain",
        "org.joml",
        "org.junit",
        "org.lwjgl",
        "org.objectweb",
        "org.polyfr",
        "org.scala",
        "org.slf4j",
        "org.spongepower",
        "org.yaml",
        "pauls",
        "scala",
        "space.lib",
        "sun",
        "tv.twit",
        "watson.cli",
        "wdl",
        "zone.rong",
        "kotlin"
    };

    /** Things totally shouldn't be transformed */
    public static String[] CONTAINS_WHITELIST = {
        "EarlyMixin",
        "LateMixin",
        "betterfps",
        "makamys",
        "mixinbooter",
        "mixinextra",
        "optifine",
        "shadersmod"
    };

    /** Mod packages that requires additional transforms */
    public static String[] BLACKLIST = {
        "com.mamiyaotaru.chatbubbles",
        "com.mamiyaotaru.jukebox",
        "com.mumfrey.worldeditcui",
        "io.totemo.watson",
        "maya.biomeborders",
        "mnm.mods.linkinfo",
        "net.easymfne.potioncolorizer",
        "net.eq2online.macros",
        "sushen",
        "troy.autofish",
        "troy.chunkborders",
        "watson",
        "xaero.mods",
        "modForge_CameraStudio"
    };

    /** Mod that requires additional transforms */
    public static String[] CONTAINS_BLACKLIST = {
        "TMI"
    };

}
