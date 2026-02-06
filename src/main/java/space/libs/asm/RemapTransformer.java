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
package space.libs.asm;

import static com.google.common.io.Resources.*;

import com.google.common.base.*;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.io.LineProcessor;
import net.minecraft.launchwrapper.IClassNameTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class RemapTransformer extends BaseRemapper implements IClassTransformer, IClassNameTransformer {

    public static String DEFAULT_MAPPINGS = "compatlib.srg";

    private final ImmutableTable<String, String, String> rawFields;
    private final ImmutableTable<String, String, String> rawMethods;
    public RemapTransformer() throws Exception {

        URL mappings = getResource(DEFAULT_MAPPINGS);

        final ImmutableBiMap.Builder<String, String> classes = ImmutableBiMap.builder();
        final ImmutableTable.Builder<String, String, String> fields = ImmutableTable.builder();
        final ImmutableTable.Builder<String, String, String> methods = ImmutableTable.builder();

        readLines(mappings, Charsets.UTF_8, new LineProcessor<Void>() {

            @Override
            public boolean processLine(String line) throws IOException {
                if ((line = line.trim()).isEmpty()) {
                    return true;
                }

                String[] parts = StringUtils.split(line, ' ');
                if (parts.length < 3) {
                    System.out.println("Invalid mapping line: " + line);
                    return true;
                }

                MappingType type = MappingType.of(parts[0]);
                if (type == null) {
                    System.out.println("Invalid mapping type: " + line);
                    return true;
                }

                String[] source;
                String[] dest;
                switch (type) {
                    case CLASS:
                        classes.put(parts[1], parts[2]);
                        break;
                    case FIELD:
                        source = getSignature(parts[1]);
                        dest = getSignature(parts[2]);
                        String fieldType = getFieldType(source[0], source[1]);
                        fields.put(source[0], source[1] + ':' + fieldType, dest[1]);
                        if (fieldType != null) {
                            fields.put(source[0], source[1] + ":null", dest[1]);
                        }
                        break;
                    case METHOD:
                        source = getSignature(parts[1]);
                        dest = getSignature(parts[3]);
                        methods.put(source[0], source[1] + parts[2], dest[1]);
                        break;
                    default:
                }

                return true;
            }

            @Override
            public Void getResult() {
                return null;
            }
        });

        ImmutableBiMap<String, String> classMap = classes.build();
        this.rawFields = fields.build();
        this.rawMethods = methods.build();
        rawFieldMaps = Maps.newHashMapWithExpectedSize(this.rawFields.size());
        rawMethodMaps = Maps.newHashMapWithExpectedSize(this.rawMethods.size());
        for (String owner : this.rawFields.rowKeySet()) {
            rawFieldMaps.put(owner, ImmutableMap.copyOf(this.rawFields.row(owner)));
        }
        for (String owner : this.rawMethods.rowKeySet()) {
            rawMethodMaps.put(owner, ImmutableMap.copyOf(this.rawMethods.row(owner)));
        }
        finalizeMappings(classMap);
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        }
        if (ClassNameList.StartsWith(name)) {
            return bytes;
        }
        if (ClassNameList.Contains(name)) {
            return bytes;
        }

        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        reader.accept(new RemappingAdapter(writer), ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
    }

    private static String[] getSignature(String in) {
        int pos = in.lastIndexOf('/');
        return new String[]{in.substring(0, pos), in.substring(pos + 1)};
    }

    private static byte[] getBytes(String name) {
        try {
            return Launch.classLoader.getClassBytes(name);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    private String getFieldType(String owner, String name) {
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
        return super.map(className);
    }

    public String unmap(String className) {
        return super.unmap(className);
    }

    @Override
    public String remapClassName(String typeName) {
        return map(typeName.replace('.', '/')).replace('/', '.');
    }

    @Override
    public String unmapClassName(String typeName) {
        return unmap(typeName.replace('.', '/')).replace('/', '.');
    }

    public void createSuperMaps(String name, String superName, String[] interfaces) {
        mergeSuperMaps(name, superName, interfaces);
    }

    @Override
    protected ParentInfo getParentInfo(String name) {
        byte[] bytes = getBytes(name);
        if (bytes == null) {
            return null;
        }
        ClassReader reader = new ClassReader(bytes);
        return new ParentInfo(reader.getSuperName(), reader.getInterfaces());
    }

    @SuppressWarnings("all")
    public String getStaticFieldType(String oldType, String oldName, String newType, String newName) {
        String type = getFieldType(oldType, oldName);
        if (oldType.equals(newType)) {
            return type;
        }
        Map<String, String> newClassMap = this.fieldDescriptions.get(newType);
        if (newClassMap == null) {
            newClassMap = Maps.newHashMap();
            this.fieldDescriptions.put(newType, newClassMap);
        }
        newClassMap.put(newName, type);
        return type;
    }

    private enum MappingType {

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

    private class RemappingAdapter extends RemappingClassAdapter {

        public RemappingAdapter(ClassVisitor cv) {
            super(cv, RemapTransformer.this);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            if (interfaces == null) {
                interfaces = ArrayUtils.EMPTY_STRING_ARRAY;
            }
            createSuperMaps(name, superName, interfaces);
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
                        String replDesc = getStaticFieldType(owner, name, type, fieldName);
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

}
