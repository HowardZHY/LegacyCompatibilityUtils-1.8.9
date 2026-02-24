/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package space.libs.asm.remap;

import static com.google.common.io.Resources.*;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.LineProcessor;
import net.minecraft.launchwrapper.IClassNameTransformer;
import org.apache.commons.lang3.*;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class DefaultRemapRemapper extends RemapperBase implements IClassNameTransformer {

    public static String DEFAULT_MAPPINGS = "compatlib.srg";

    private final ImmutableBiMap<String, String> classes;
    private final ImmutableTable<String, String, String> rawFields;
    private final ImmutableTable<String, String, String> rawMethods;

    private final Map<String, Map<String, String>> fields;
    private final Map<String, Map<String, String>> methods;

    final ImmutableBiMap.Builder<String, String> classesIn = ImmutableBiMap.builder();
    final ImmutableTable.Builder<String, String, String> fieldsIn = ImmutableTable.builder();
    final ImmutableTable.Builder<String, String, String> methodsIn = ImmutableTable.builder();

    private final Set<String> failedFields = Sets.newHashSet();
    private final Set<String> failedMethods = Sets.newHashSet();

    public DefaultRemapRemapper() {
        super(DEFAULT_MAPPINGS);
        try {
            readLines(mappings, Charsets.UTF_8, new MappingLineProcessor());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.classes = classesIn.build();
        this.rawFields = fieldsIn.build();
        this.rawMethods = methodsIn.build();
        this.fields = Maps.newHashMapWithExpectedSize(this.rawFields.size());
        this.methods = Maps.newHashMapWithExpectedSize(this.rawMethods.size());
    }

    private static String[] getSignature(String in) {
        int pos = in.lastIndexOf('/');
        return new String[]{in.substring(0, pos), in.substring(pos + 1)};
    }

    protected String getFieldType(String owner, String name) {
        Map<String, String> fieldDescriptions = this.fieldDescriptions.get(owner);
        if (fieldDescriptions != null) {
            return fieldDescriptions.get(name);
        }
        byte[] bytes = getBytes(owner);
        if (bytes == null) {
            return null;
        }
        ClassReader reader = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        String result = null;
        fieldDescriptions = Maps.newHashMapWithExpectedSize(classNode.fields.size());
        for (FieldNode fieldNode : classNode.fields) {
            fieldDescriptions.put(fieldNode.name, fieldNode.desc);
            if (fieldNode.name.equals(name)) {
                result = fieldNode.desc;
            }
        }
        this.fieldDescriptions.put(owner, fieldDescriptions);
        return result;
    }

    @Override
    public String map(String className) {
        if (this.classes == null) {
            return className;
        }
        String name = this.classes.get(className);
        if (name != null) {
            return name;
        }
        // We may have no name for the inner class directly, but it should be still part of the outer class
        int innerClassPos = className.lastIndexOf('$');
        if (innerClassPos >= 0) {
            return map(className.substring(0, innerClassPos)) + className.substring(innerClassPos);
        }
        return className; // Unknown class
    }

    public String unmap(String className) {
        if (this.classes == null) {
            return className;
        }
        String name = this.classes.inverse().get(className);
        if (name != null) {
            return name;
        }
        // We may have no name for the inner class directly, but it should be still part of the outer class
        int innerClassPos = className.lastIndexOf('$');
        if (innerClassPos >= 0) {
            return unmap(className.substring(0, innerClassPos)) + className.substring(innerClassPos);
        }
        return className; // Unknown class
    }

    @Override
    public String mapFieldName(String owner, String fieldName, String desc) {
        if (this.classes == null) {
            return fieldName;
        }
        Map<String, String> fields = getFieldMap(owner);
        if (fields != null) {
            String name = fields.get(fieldName + ':' + desc);
            if (name != null) {
                return name;
            } else {
                try {
                    if (fields.get(name + ":null") != null) {
                        if (DEBUG_REMAPPING && (!owner.contains("/") || owner.contains("net"))) {
                            LOGGER.info("Try map field without desc " + owner + "." + name + " to " + fields.get(name + ":null"));
                        }
                        return fields.get(name + ":null");
                    }
                } catch (NullPointerException ignored) {}
            }
        }
        return fieldName;
    }

    private Map<String, String> getFieldMap(String owner) {
        Map<String, String> result = this.fields.get(owner);
        if (result != null) {
            return result;
        }
        if (!this.failedFields.contains(owner)) {
            loadSuperMaps(owner);
            if (!this.fields.containsKey(owner)) {
                this.failedFields.add(owner);
            }
        }
        return this.fields.get(owner);
    }

    @Override
    public String mapMethodName(String owner, String methodName, String desc) {
        if (this.classes == null) {
            return methodName;
        }
        Map<String, String> methods = getMethodMap(owner);
        if (methods != null) {
            String name = methods.get(methodName + desc);
            if (name != null) {
                return name;
            }
        }
        return methodName;
    }

    private Map<String, String> getMethodMap(String owner) {
        Map<String, String> result = this.methods.get(owner);
        if (result != null) {
            return result;
        }
        if (!this.failedMethods.contains(owner)) {
            loadSuperMaps(owner);
            if (!this.methods.containsKey(owner)) {
                this.failedMethods.add(owner);
            }
        }
        return this.methods.get(owner);
    }

    @Override
    public String remapClassName(String typeName) {
        return map(typeName.replace('.', '/')).replace('/', '.');
    }

    @Override
    public String unmapClassName(String typeName) {
        return unmap(typeName.replace('.', '/')).replace('/', '.');
    }

    private void loadSuperMaps(String name) {
        byte[] bytes = getBytes(name);
        if (bytes != null) {
            ClassReader reader = new ClassReader(bytes);
            createSuperMaps(name, reader.getSuperName(), reader.getInterfaces());
        }
    }

    public void createSuperMaps(String name, String superName, String[] interfaces) {
        if (Strings.isNullOrEmpty(superName)) {
            return;
        }
        String[] parents = new String[interfaces.length + 1];
        parents[0] = superName;
        System.arraycopy(interfaces, 0, parents, 1, interfaces.length);
        for (String parent : parents) {
            if (!this.fields.containsKey(parent)) {
                loadSuperMaps(parent);
            }
        }
        Map<String, String> fields = Maps.newHashMap();
        Map<String, String> methods = Maps.newHashMap();

        Map<String, String> m;
        for (String parent : parents) {
            m = this.fields.get(parent);
            if (m != null) {
                fields.putAll(m);
            }
            m = this.methods.get(parent);
            if (m != null) {
                methods.putAll(m);
            }
        }
        fields.putAll(this.rawFields.row(name));
        methods.putAll(this.rawMethods.row(name));
        this.fields.put(name, ImmutableMap.copyOf(fields));
        this.methods.put(name, ImmutableMap.copyOf(methods));
    }

    public static class RemappingAdapter extends RemappingClassAdapter {

        public static DefaultRemapRemapper INSTANCE;

        public RemappingAdapter(ClassVisitor cv, DefaultRemapRemapper instace) {
            super(cv, instace);
            INSTANCE = instace;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            if (interfaces == null) {
                interfaces = new String[0];
            }
            INSTANCE.createSuperMaps(name, superName, interfaces);
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        protected MethodVisitor createRemappingMethodAdapter(int access, String newDesc, MethodVisitor mv) {
            return new RemappingMethodAdapter(access, newDesc, mv, RemappingAdapter.this.remapper) {

                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                    String type = this.remapper.mapType(owner);
                    String fieldName = this.remapper.mapFieldName(owner, name, desc);
                    String newDesc = this.remapper.mapDesc(desc);
                    if ((opcode == Opcodes.GETSTATIC) && type.startsWith("net/minecraft/") && newDesc.startsWith("Lnet/minecraft/")) {
                        String replDesc = INSTANCE.getStaticFieldType(owner, name, type, fieldName);
                        if (replDesc != null) {
                            newDesc = this.remapper.mapDesc(replDesc);
                        }
                    }
                    if (this.mv != null) {
                        this.mv.visitFieldInsn(opcode, type, fieldName, newDesc);
                    }
                }
            };
        }
    }

    public class MappingLineProcessor implements LineProcessor<Void> {

        @SuppressWarnings("all")
        @Override
        public boolean processLine(String line) {
            if ((line = line.trim()).isEmpty()) {
                return true;
            }
            String[] parts = StringUtils.split(line, ' ');
            if (parts.length < 3) {
                LOGGER.error("Invalid mapping line: " + line);
                return true;
            }
            MappingType type = MappingType.of(parts[0]);
            if (type == null) {
                LOGGER.error("Invalid mapping type: " + line);
                return true;
            }
            String[] source;
            String[] dest;
            switch (type) {
                case CLASS:
                    classesIn.put(parts[1], parts[2]);
                    break;
                case FIELD:
                    source = getSignature(parts[1]);
                    dest = getSignature(parts[2]);
                    String fieldType = getFieldType(source[0], source[1]);
                    fieldsIn.put(source[0], source[1] + ':' + fieldType, dest[1]);
                    if (fieldType != null) {
                        fieldsIn.put(source[0], source[1] + ":null", dest[1]);
                    }
                    break;
                case METHOD:
                    source = getSignature(parts[1]);
                    dest = getSignature(parts[3]);
                    methodsIn.put(source[0], source[1] + parts[2], dest[1]);
                    break;
                default:
            }
            return true;
        }

        @Override
        public Void getResult() {
            return null;
        }
    }
}
