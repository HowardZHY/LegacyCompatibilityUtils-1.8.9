package space.libs.asm;

import com.google.common.primitives.Bytes;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ReplaceTransformer implements IClassTransformer {

    public static final String FML_OLD = "cpw/mods/fml";

    public static final String FML = "net/minecraftforge/fml";

    public static final String UTIL_MATH = "net/minecraft/util/math";

    public static final String UTIL = "net/minecraft/util";

    public static List<String> PACKAGE_PREFIXES = Arrays.asList(
        FML_OLD,
        UTIL_MATH
    );

    public static List<byte[]> PACKAGE_PREFIXES_RAW = PACKAGE_PREFIXES.stream()
        .map(s -> s.getBytes(StandardCharsets.UTF_8))
        .collect(Collectors.toList());

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (ClassNameList.ShouldNotTransform(name)) {
            return bytes;
        }
        boolean found = containsAnyPattern(bytes, PACKAGE_PREFIXES_RAW);
        if (!found) return bytes;
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, 0);
        RemappingClassAdapter remapAdapter = new ReplaceRemappingAdapter(writer);
        reader.accept(remapAdapter, ClassReader.EXPAND_FRAMES);
        bytes = writer.toByteArray();
        return bytes;
    }

    public static boolean containsAnyPattern(byte[] array, List<byte[]> patterns) {
        if (array == null) {
            return false;
        }
        for (byte[] pattern : patterns) {
            if (Bytes.indexOf(array, pattern) != -1) {
                return true;
            }
        }
        return false;
    }

    public static String getPackagePrefix(int index) {
        return PACKAGE_PREFIXES.get(index);
    }

    public static class ReplaceRemappingAdapter extends RemappingClassAdapter {
        public ReplaceRemappingAdapter(ClassWriter classWriter) {
            super(classWriter, ReplaceRemapper.INSTANCE);
        }
    }

    public static class ReplaceRemapper extends Remapper {

        public static Remapper INSTANCE = new ReplaceRemapper();

        @Override
        public String map(String typeName) {
            if (typeName.startsWith(FML_OLD)) {
                return FML + typeName.substring(FML_OLD.length());
            } else {
                if (typeName.startsWith(UTIL_MATH)) {
                    return UTIL + typeName.substring(UTIL_MATH.length());
                }
            }
            return super.map(typeName);
        }

        @Override
        public String mapDesc(String desc) {
            if (desc.startsWith("L" + FML_OLD)) {
                return "L" + FML + desc.substring(FML_OLD.length() + 1);
            }
            // TODO?
            return super.mapDesc(desc);
        }
    }

}
