/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */

package space.libs.asm.remap;

import com.google.common.base.Charsets;
import com.google.common.collect.*;
import com.google.common.io.LineProcessor;
import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.commons.Remapper;
import space.libs.core.CompatLibDebug;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@SuppressWarnings({"UnstableApiUsage", "CommentedOutCode"})
public abstract class RemapperBase extends Remapper {

    public static final Logger LOGGER = LogManager.getLogger();

    public static boolean DEBUG_REMAPPING = CompatLibDebug.DEBUG_REMAP;

    public RemapperBase(final String file, final Boolean deobfuscating) {
        this.mappings = Resources.getResource(file);
        this.legacy = deobfuscating;
        this.fieldDescriptions = Maps.newHashMap();
        this.rawFieldMaps = Maps.newHashMap();
        this.rawMethodMaps = Maps.newHashMap();
        this.classesIn = ImmutableBiMap.builder();
        this.fieldsIn = ImmutableTable.builder();
        this.methodsIn = ImmutableTable.builder();
        this.negativeFields = Sets.newHashSet();
        this.negativeMethods = Sets.newHashSet();
        this.setup();
        this.rawFields = fieldsIn.build();
        this.rawMethods = methodsIn.build();
        this.classesBiMap = classesIn.build();
        this.reverseClassMap = classesBiMap.inverse();
        if (deobfuscating) {
            this.fieldsMap = Maps.newHashMapWithExpectedSize(this.rawFieldMaps.size());
            this.methodsMap = Maps.newHashMapWithExpectedSize(this.rawMethodMaps.size());
        } else {
            this.fieldsMap = Maps.newHashMapWithExpectedSize(this.rawFields.size());
            this.methodsMap = Maps.newHashMapWithExpectedSize(this.rawMethods.size());
        }
    }

    protected final URL mappings;
    public final Boolean legacy;

    public final ImmutableBiMap<String, String> classesBiMap;
    public final ImmutableBiMap<String, String> reverseClassMap;

    protected final ImmutableBiMap.Builder<String, String> classesIn;
    protected final ImmutableTable.Builder<String, String, String> fieldsIn;
    protected final ImmutableTable.Builder<String, String, String> methodsIn;

    protected final ImmutableTable<String, String, String> rawFields;
    protected final ImmutableTable<String, String, String> rawMethods;

    protected final Map<String, Map<String, String>> rawFieldMaps;
    protected final Map<String, Map<String, String>> rawMethodMaps;

    protected final Map<String, Map<String, String>> fieldsMap;
    protected final Map<String, Map<String, String>> methodsMap;

    protected final Set<String> negativeFields;
    protected final Set<String> negativeMethods;

    protected final Map<String, Map<String, String>> fieldDescriptions;

    protected void setup() {
        try {
            Resources.readLines(mappings, Charsets.UTF_8, new MappingLineProcessor());
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    protected abstract String getFieldType(String owner, String name);

    public Map<String, String> getFieldMap(String owner) {
        Map<String, String> result = this.fieldsMap.get(owner);
        if (result != null) {
            return result;
        }
        if (!this.negativeFields.contains(owner)) {
            loadSuperMaps(owner);
            if (!this.fieldsMap.containsKey(owner)) {
                this.negativeFields.add(owner);
            }
            /*if (DEBUG_REMAPPING && !owner.startsWith("java")) {
                LOGGER.info("Field map for " + owner + " : " + fieldsMap.get(owner));
            }*/
        }
        return this.fieldsMap.get(owner);
    }

    public Map<String, String> getMethodMap(String owner) {
        Map<String, String> result = this.methodsMap.get(owner);
        if (result != null) {
            return result;
        }
        if (!this.negativeMethods.contains(owner)) {
            loadSuperMaps(owner);
            if (!this.methodsMap.containsKey(owner)) {
                this.negativeMethods.add(owner);
            }
            /*if (DEBUG_REMAPPING && !owner.startsWith("java")) {
                LOGGER.info("Method map for " + owner + " : " + methodsMap.get(owner));
            }*/
        }
        return this.methodsMap.get(owner);
    }

    @Override
    public String map(String typeName) {
        if (this.noClasses()) {
            return typeName;
        }
        String name = this.classesBiMap.get(typeName);
        if (name != null) {
            return name;
        }
        int inner = typeName.lastIndexOf('$');
        if (inner > -1) {
            return this.map(typeName.substring(0, inner)) + "$" + typeName.substring(inner + 1);
        }
        return typeName;
    }

    public String unmap(String typeName) {
        if (this.noClasses()) {
            return typeName;
        }
        String name = this.reverseClassMap.get(typeName);
        if (name != null) {
            return name;
        }
        int inner = typeName.lastIndexOf('$');
        if (inner > -1) {
            return this.unmap(typeName.substring(0, inner)) + "$" + typeName.substring(inner + 1);
        }
        return typeName;
    }

    @Override
    public String mapFieldName(String owner, String fieldName, String desc) {
        if (this.noClasses()) {
            return fieldName;
        }
        Map<String, String> fields = getFieldMap(owner);
        if (fields != null) {
            String name = fields.get(fieldName + ':' + desc);
            if (name != null) {
                return name;
            } else { //TODO
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

    @Override
    public String mapMethodName(String owner, String name, String desc) {
        if (this.noClasses()) {
            return name;
        }
        Map<String, String> methods = getMethodMap(owner);
        if (methods != null) {
            String mapped = methods.get(name + desc);
            if (mapped != null) {
                return mapped;
            }
        }
        return name;
    }

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

    public void loadSuperMaps(String name) {}

    public void mergeSuperMaps(String name, String superName, String[] interfaces) {}

    public boolean noClasses() {
        return this.classesBiMap == null || this.classesBiMap.isEmpty();
    }

    @SuppressWarnings("unused")
    public boolean isRemappedClass(String className) {
        return !map(className).equals(className);
    }

    public static String[] getSignature(String in) {
        int pos = in.lastIndexOf('/');
        return new String[]{in.substring(0, pos), in.substring(pos + 1)};
    }

    public enum MappingType {

        PACKAGE("PK"), CLASS("CL"), FIELD("FD"), METHOD("MD");

        private static final ImmutableMap<String, MappingType> LOOKUP;

        private final String identifier;

        MappingType(final String identifier) {
            this.identifier = identifier;
        }

        static {
            ImmutableMap.Builder<String, MappingType> builder = ImmutableMap.builder();
            for (MappingType type : MappingType.values()) {
                builder.put(type.identifier + ':', type);
            }
            LOOKUP = builder.build();
        }

        public static MappingType of(final String identifier) {
            return LOOKUP.get(identifier);
        }
    }

    @SuppressWarnings("all")
    public class MappingLineProcessor implements LineProcessor<Void> {

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
