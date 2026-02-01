package space.libs.asm;

import java.util.Arrays;

@SuppressWarnings("SpellCheckingInspection")
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
    public static String[] WHITELIST;

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

    static {
        WHITELIST = new String[] {
            "barit",
            "club.sk1er.patch",
            "com.goog",
            "com.gtnewh",
            "com.hbm",
            "com.ibm.",
            "com.jcraft",
            "com.llamalad",
            "com.mojang",
            "com.mumfrey.lite",
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
            "gnu.trove",
            "groovy",
            "io.github.legacymod",
            "io.netty",
            "it.unimi",
            "java",
            "jopt",
            "junit",
            "net.java.",
            "net.jpointz",
            "net.md_5.spec",
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
            "org.scala",
            "org.slf4j",
            "org.spongepower",
            "org.yaml",
            "pauls",
            "scala",
            "sun.",
            "space.lib",
            "tv.twit",
            "watson.cli",
            "wdl",
            "zone.rong",
            "kotlin",

            "cc.polyfr",
            "ch.qos.log",
            "co.skycli",
            "com.jacob",
            "com.labymed",
            "gg.skytil",
            "io.github.moulberry",
            "me.jellysquid.mods.ph",
            "moe.nea",
            "net.ccbluex",
            "net.labymod",
            "net.minecraft",
            "net.minecraftforge.fml",
            "org.polyfr",
        };
    }
}
