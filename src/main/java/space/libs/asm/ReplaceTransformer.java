package space.libs.asm;

import com.google.common.primitives.Bytes;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class ReplaceTransformer implements IClassTransformer {

    public ReplaceTransformer() {
        this.PACKAGE.put(BLOCKPOS_NEW, BLOCKPOS);
        this.PACKAGE.put(AABB_NEW, AABB);
    }

    public static String BLOCKPOS_NEW = "net/minecraft/util/math/BlockPos";

    public static String BLOCKPOS = "net/minecraft/util/BlockPos";

    public static String AABB_NEW = "net/minecraft/util/math/AxisAlignedBB";

    public static String AABB = "net/minecraft/util/AxisAlignedBB";

    public static Map<String, String> PACKAGE = new HashMap<>();

    public static List<String> PACKAGE_PREFIXES = Arrays.asList(
        BLOCKPOS_NEW,
        AABB_NEW
    );

    public static List<byte[]> PACKAGE_PREFIXES_RAW = PACKAGE_PREFIXES.stream()
        .map(s -> s.getBytes(StandardCharsets.UTF_8))
        .collect(Collectors.toList());

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return null;
        }
        if (ClassNameList.Contains(name) || ClassNameList.Startswith(name)) {
            return bytes;
        }
        boolean found = containsAnyPattern(bytes, getPackagePrefixesRaw());
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

    public static List<byte[]> getPackagePrefixesRaw() {
        return PACKAGE_PREFIXES_RAW;
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
            for (Map.Entry<String, String> entry : PACKAGE.entrySet()) {
                String oldPrefix = entry.getKey();
                String newPrefix = entry.getValue();
                if (typeName.startsWith(oldPrefix)) {
                    return newPrefix + typeName.substring(oldPrefix.length());
                }
            }
            return super.map(typeName);
        }
    }

}
