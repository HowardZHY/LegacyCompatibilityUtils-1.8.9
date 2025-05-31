package space.libs.mixins.forge;

import net.minecraft.block.Block;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.CompatLib;
import space.libs.interfaces.IFMLControlledNamespacedRegistry;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import java.util.BitSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
@Mixin(value = FMLControlledNamespacedRegistry.class, remap = false)
public class MixinFMLControlledNamespacedRegistry<K, I> extends RegistryNamespacedDefaultedByKey<K, Object> implements IFMLControlledNamespacedRegistry {

    public MixinFMLControlledNamespacedRegistry(K defaultValueKeyIn) {
        super(defaultValueKeyIn);
    }

    @SuppressWarnings("all")
    @ShadowConstructor
    void FMLControlledNamespacedRegistry(ResourceLocation defaultKey, int maxIdValue, int minIdValue, Class<I> type, boolean isDelegated) {}

    @Shadow
    void validateContent(ResourceLocation registryName) {}

    @Shadow
    void set(FMLControlledNamespacedRegistry<I> otherRegistry) {}

    @Shadow
    public void register(int id, ResourceLocation name, Object thing) {}

    @Shadow
    public void putObject(ResourceLocation name, Object thing) {}

    @Shadow
    public Object getObject(ResourceLocation name) {
        throw new AbstractMethodError();
    }

    @Shadow
    public boolean containsKey(ResourceLocation name) {
        throw new AbstractMethodError();
    }

    @Shadow
    public int getId(ResourceLocation itemName) {
        throw new AbstractMethodError();
    }

    @Shadow
    private I getRaw(ResourceLocation name) {
        throw new AbstractMethodError();
    }

    @Shadow
    public Iterable<I> typeSafeIterable() {
        throw new AbstractMethodError();
    }

    @Shadow
    int add(int id, ResourceLocation name, Object thing) {
        throw new AbstractMethodError();
    }

    @Shadow
    void addAlias(ResourceLocation from, ResourceLocation to) {}

    @Shadow(prefix = "original$")
    private void original$addObjectRaw(int id, ResourceLocation name, Object thing) {}

    @Shadow
    Object activateSubstitution(ResourceLocation nameToReplace) {
        throw new AbstractMethodError();
    }

    @SuppressWarnings("all")
    @Shadow
    void addSubstitutionAlias(String modId, ResourceLocation nameToReplace, Object replacement) throws ExistingSubstitutionException {}

    @NewConstructor
    public void FMLControlledNamespacedRegistry(Object defaultKey, int maxIdValue, int minIdValue, Class<I> type) {
        FMLControlledNamespacedRegistry(new ResourceLocation((String) defaultKey), maxIdValue, minIdValue, type, false);
    }

    public void validateContent(int maxId, String type, BitSet availabilityMap, Set<Integer> blockedIds, FMLControlledNamespacedRegistry<Block> iBlockRegistry) {
        this.validateContent(new ResourceLocation(type));
    }

    @SuppressWarnings("unchecked")
    public void setFrom(FMLControlledNamespacedRegistry<?> registry) {
        this.set((FMLControlledNamespacedRegistry<I>) registry);
    }

    @Override
    public void register(int id, Object name, Object thing) {
        if (name == null || thing == null) {
            throw new NullPointerException();
        }
        if (name instanceof ResourceLocation) {
            this.register(id, (ResourceLocation) name, thing);
            return;
        }
        String name1 = name.toString();
        if (name1.isEmpty()) {
            throw new IllegalArgumentException("Can't use an empty name for the registry.");
        }
        this.register(id, new ResourceLocation(name1), thing);
    }

    @Override
    public void putObject(Object objName, Object obj) {
        if (objName == null || obj == null) {
            throw new NullPointerException();
        }
        if (objName instanceof ResourceLocation) {
            this.putObject((ResourceLocation) objName, obj);
            return;
        }
        String name = objName.toString();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Can't use an empty name for the registry.");
        }
        this.putObject(new ResourceLocation(name), obj);
    }

    @Override
    public Object getObject(K name) {
        if (name instanceof ResourceLocation) {
            return this.getObject((ResourceLocation) name);
        }
        return this.getObject(new ResourceLocation(name.toString()));
    }

    public Object get(int id) {
        return getObjectById(id);
    }

    public Object get(String name) {
        return getObject(new ResourceLocation(name));
    }

    @SuppressWarnings("unchecked")
    public I cast(Object obj) {
        return (I)(obj);
    }

    public Object getRaw(String name) {
        return getRaw(new ResourceLocation(name));
    }

    public int getId(String itemName) {
        return getId(new ResourceLocation(itemName));
    }

    @Override
    public boolean containsKey(K key) {
        if (key instanceof ResourceLocation) {
            return this.containsKey((ResourceLocation) key);
        }
        return this.containsKey(new ResourceLocation(key.toString()));
    }

    public boolean contains(String itemName) {
        return containsKey(new ResourceLocation(itemName));
    }

    public void serializeInto(Map<String, Integer> idMapping) {
        for (I thing : this.typeSafeIterable()) {
            idMapping.put(getNameForObject(thing).toString(), getId(thing.toString()));
        }
    }

    public BitSet internalAvailabilityMap = new BitSet();

    public int add(int id, String name, Object thing) {
        return this.add(id, new ResourceLocation(name), thing);
    }

    public int add(int id, String name, Object thing, BitSet availabilityMap) {
        return this.add(id, new ResourceLocation(name), thing);
    }

    public void addAlias(String from, String to) {
        this.addAlias(new ResourceLocation(from), new ResourceLocation(to));
    }

    public void dump() {
        try {
            throw new UnsupportedOperationException();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void addObjectRaw(int id, Object name, I thing) {
        if (name == null || thing == null) {
            throw new NullPointerException();
        }
        if (name instanceof ResourceLocation) {
            this.original$addObjectRaw(id, (ResourceLocation) name, thing);
            return;
        }
        this.original$addObjectRaw(id, new ResourceLocation(name.toString()), thing);
    }

    public void activateSubstitution(String nameToReplace) {
        this.activateSubstitution(new ResourceLocation(nameToReplace));
    }

    public void addSubstitutionAlias(String modId, String nameToReplace, Object toReplace) throws ExistingSubstitutionException {
        this.addSubstitutionAlias(modId, new ResourceLocation(nameToReplace), toReplace);
    }

    public void AddObjectRaw(int id, ResourceLocation name, Object thing) {
        if (thing == null) {
            throw new NullPointerException();
        }
        if (name == null) {
            CompatLib.LOGGER.warn("Object" + thing + " with ID " + id + "has null name?");
            return;
        }
        this.original$addObjectRaw(id, name, thing);
    }
}
