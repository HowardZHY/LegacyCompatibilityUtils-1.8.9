/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.forge.common;

import com.google.common.collect.*;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import space.libs.interfaces.IFMLControlledNamespacedRegistry;
import space.libs.util.forge.ResourcesLocationUtils;

import java.util.*;

@SuppressWarnings("unused")
public class GameDataSnapshot {

    public static class Entry {

        public final Map<String, Integer> ids;
        public final Set<String> substitutions;
        public final Map<String, String> aliases;
        public final Set<Integer> blocked;

        public Entry() {
            this(new HashMap<>(), new HashSet<>(), new HashMap<>(), new HashSet<>());
        }

        public Entry(Map<String, Integer> ids, Set<String> substitions, Map<String, String> aliases, Set<Integer> blocked) {
            this.ids = ids;
            this.substitutions = substitions;
            this.aliases = aliases;
            this.blocked = blocked;
        }

        public Entry(FMLControlledNamespacedRegistry<?> registry) {
            this.ids = Maps.newHashMap();
            this.substitutions = Sets.newHashSet();
            this.aliases = Maps.newHashMap();
            this.blocked = Sets.newHashSet();
            ((IFMLControlledNamespacedRegistry) registry).serializeInto(this.ids);
            registry.serializeSubstitutions(ResourcesLocationUtils.convertSet(this.substitutions));
            registry.serializeAliases(ResourcesLocationUtils.convertMap(this.aliases));
            // if (GameData.getBlockRegistry() == registry || GameData.getItemRegistry() == registry) { this.blocked.addAll(GameData.getMain().blockedIds); }
        }
    }

    public final Map<String, Entry> entries = Maps.newHashMap();

}
