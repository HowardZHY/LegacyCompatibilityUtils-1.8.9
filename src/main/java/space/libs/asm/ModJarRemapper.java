package space.libs.asm;

import net.md_5.specialsource.*;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

public class ModJarRemapper extends JarRemapper {

    public JarMapping jarMapping;

    public RemapperProcessor preProcessor;

    public RemapperProcessor postProcessor;

    public ModJarRemapper(RemapperProcessor preProcessor, JarMapping jarMapping, RemapperProcessor postProcessor) {
        super(preProcessor, jarMapping, postProcessor);
        this.preProcessor = preProcessor;
        this.jarMapping = jarMapping;
        this.postProcessor = postProcessor;
    }

    public ModJarRemapper(JarMapping jarMapping) {
        this(null, jarMapping, null);
    }

    @Override
    public String map(String typeName) {
        return mapTypeName(typeName, this.jarMapping.packages, this.jarMapping.classes, typeName);
    }

    public static String mapTypeName(String typeName, Map<String, String> packageMap, Map<String, String> classMap, String defaultIfUnmapped) {
        String mapped = mapClassName(typeName, packageMap, classMap);
        return mapped != null ? mapped : defaultIfUnmapped;
    }

    /**
     * Helper method to map a class name by package (prefix) or class (exact)
     */
    private static String mapClassName(String className, Map<String, String> packageMap, Map<String, String> classMap) {
        if (classMap != null && classMap.containsKey(className)) {
            return classMap.get(className);
        }

        int index = className.lastIndexOf('$');
        if (index != -1)
        {
            String outer = className.substring(0, index);
            String mapped = mapClassName(outer, packageMap, classMap);
            if  (mapped == null) return null;
            return mapped + className.substring(index);
        }

        if (packageMap != null) {
            for (String oldPackage : packageMap.keySet()) {
                if (matchClassPackage(oldPackage, className)) {
                    String newPackage = packageMap.get(oldPackage);

                    return moveClassPackage(newPackage, getSimpleName(oldPackage, className));
                }
            }
        }

        return null;
    }

    private static boolean matchClassPackage(String packageName, String className) {
        if (packageName.equals(".")) {
            return isDefaultPackage(className);
        }

        return className.startsWith(packageName);
    }

    private static String moveClassPackage(String packageName, String classSimpleName) {
        if (packageName.equals(".")) {
            return classSimpleName;
        }

        return packageName + classSimpleName;
    }

    private static boolean isDefaultPackage(String className) {
        return className.indexOf('/') == -1;
    }

    private static String getSimpleName(String oldPackage, String className) {
        if (oldPackage.equals(".")) {
            return className;
        }

        return className.substring(oldPackage.length());
    }

    @Override
    public String mapFieldName(String owner, String name, String desc, int access) {
        String mapped = jarMapping.tryClimb(jarMapping.fields, NodeType.FIELD, owner, name, access);
        return mapped == null ? name : mapped;
    }

    @Override
    public String mapMethodName(String owner, String name, String desc, int access) {
        String mapped = jarMapping.tryClimb(jarMapping.methods, NodeType.METHOD, owner, name + " " + desc, access);
        return mapped == null ? name : mapped;
    }

    @Override
    public byte[] remapClassFile(InputStream is, ClassRepo repo) throws IOException {
        return remapClassFile(new ClassReader(is), repo);
    }

    @Override
    public byte[] remapClassFile(byte[] in, ClassRepo repo) {
        return remapClassFile(new ClassReader(in), repo);
    }

    public byte[] remapClassFile(ClassReader reader, final ClassRepo repo) {
        if (preProcessor != null) {
            byte[] pre = preProcessor.process(reader);
            if (pre != null) {
                reader = new ClassReader(pre);
            }
        }

        ClassNode node = new ClassNode();
        RemappingClassAdapter mapper = new RemappingClassAdapter(node, this, repo);
        int readerFlags = 0;
        reader.accept(mapper, readerFlags);

        ClassWriter wr = new ClassWriter(COMPUTE_MAXS);
        node.accept(wr);
        if (SpecialSource.identifier != null) {
            wr.newUTF8(SpecialSource.identifier);
        }

        return (postProcessor != null) ? postProcessor.process(wr.toByteArray()) : wr.toByteArray();
    }
}
