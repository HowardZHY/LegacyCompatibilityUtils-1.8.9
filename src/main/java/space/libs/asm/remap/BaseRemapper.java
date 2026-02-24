package space.libs.asm.remap;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.commons.Remapper;
import space.libs.core.CompatLibDebug;

import java.io.IOException;
import java.util.Map;

public abstract class BaseRemapper extends Remapper {

    public static final Logger LOGGER = LogManager.getLogger();

    public static boolean DEBUG_REMAPPING = CompatLibDebug.DEBUG_REMAP;

    protected Map<String, Map<String,String>> fieldDescriptions = Maps.newHashMap();

    public BaseRemapper() {}

    protected abstract String getFieldType(String owner, String name);

    @SuppressWarnings("Java8MapApi")
    public String getStaticFieldType(String oldType, String oldName, String newType, String newName) {
        String fType = getFieldType(oldType, oldName);
        if (DEBUG_REMAPPING) {
            LOGGER.info("Static Field: " + oldType + "+" + oldName + " & " + newType + "+" + newName + " fT: " + fType);
        }
        if (oldType.equals(newType)) {
            return fType;
        }
        Map<String,String> newClassMap = this.fieldDescriptions.get(newType);
        if (newClassMap == null) {
            newClassMap = Maps.newHashMap();
            this.fieldDescriptions.put(newType, newClassMap);
        }
        newClassMap.put(newName, fType);
        return fType;
    }

    public static byte[] getBytes(String name) {
        try {
            return Launch.classLoader.getClassBytes(name);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}
