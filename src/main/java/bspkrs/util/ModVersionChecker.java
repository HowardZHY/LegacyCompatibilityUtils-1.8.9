package bspkrs.util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

@SuppressWarnings("unused")
public class ModVersionChecker {

    public static final Map<String, ModVersionChecker> versionCheckerMap = new HashMap<>();

    public URL versionURL;

    public final String modID;

    public String newVersion;

    public final String currentVersion;

    public String updateURL;

    public String[] loadMsg;

    public String[] inGameMsg;

    public File trackerFile;

    public File trackerDir;

    public static Preferences versionCheckTracker;

    public String lastNewVersionFound;

    public final String CHECK_ERROR;

    public final boolean errorDetected;

    public int runsSinceLastMessage;

    public ModVersionChecker(String modID, String curVer, String versionURL, String updateURL, String[] loadMsg, String[] inGameMsg) {
        this(modID, curVer, versionURL, updateURL, loadMsg, inGameMsg, 0);
    }

    public ModVersionChecker(String modID, String curVer, String versionURL, String updateURL, String[] loadMsg, String[] inGameMsg, int timeoutMS) {
        this.CHECK_ERROR = "check_error";
        this.modID = modID;
        this.currentVersion = curVer;
        this.updateURL = updateURL;
        this.loadMsg = loadMsg;
        this.inGameMsg = inGameMsg;
        this.errorDetected = false;
        this.runsSinceLastMessage = -1;
    }

    public ModVersionChecker(String modName, String oldVer, String versionURL, String updateURL) {
        this(modName, oldVer, versionURL, updateURL, new String[]{""}, new String[]{""});
    }

    public void checkVersionWithLogging() {}

    public void setLoadMessage(String[] loadMsg) {}

    public void setInGameMessage(String[] inGameMsg) {}

    public String[] getLoadMessage() {
        return this.loadMsg;
    }

    public String[] getInGameMessage() {
        return this.inGameMsg;
    }

    public URL getVersionURL() {
        return this.versionURL;
    }

    public String getNewVersion() {
        return this.newVersion;
    }

    public String getCurrentVersion() {
        return this.currentVersion;
    }

    public String getUpdateURL() {
        return this.updateURL;
    }

    public static Map<String, ModVersionChecker> getVersionCheckerMap() {
        return versionCheckerMap;
    }

    public boolean isCurrentVersion() {
        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isCurrentVersion(String oldVer, String newVer) {
        return true;
    }

    public String replaceAllTags(String s) {
        return s.replace("{oldVer}", this.currentVersion).replace("{newVer}", this.newVersion).replace("{modID}", this.modID).replace("{updateURL}", this.updateURL);
    }

    public static String[] checkVersionForMod(String modID) {
        return new String[]{"Version check is no longer available."};
    }
}

