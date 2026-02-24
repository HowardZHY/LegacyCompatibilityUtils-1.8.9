package space.libs.asm.remap;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.commons.Remapper;
import space.libs.core.CompatLibDebug;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static com.google.common.io.Resources.getResource;

@SuppressWarnings("UnstableApiUsage")
public abstract class RemapperBase extends Remapper {

    public static final Logger LOGGER = LogManager.getLogger();

    public static boolean DEBUG_REMAPPING = CompatLibDebug.DEBUG_REMAP;

    public RemapperBase(String file) {
        this.mappings = getResource(file);
    }

    protected Map<String, Map<String,String>> fieldDescriptions = Maps.newHashMap();

    protected URL mappings;

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

    public enum MappingType {

        PACKAGE("PK"), CLASS("CL"), FIELD("FD"), METHOD("MD");

        private static final ImmutableMap<String, MappingType> LOOKUP;

        private final String identifier;

        MappingType(String identifier) {
            this.identifier = identifier;
        }

        static {
            ImmutableMap.Builder<String, MappingType> builder = ImmutableMap.builder();
            for (MappingType type : MappingType.values()) {
                builder.put(type.identifier + ':', type);
            }
            LOOKUP = builder.build();
        }

        public static MappingType of(String identifier) {
            return LOOKUP.get(identifier);
        }
    }
}
