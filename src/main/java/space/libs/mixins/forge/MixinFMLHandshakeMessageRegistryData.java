/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import net.minecraftforge.fml.common.registry.PersistentRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.forge.common.GameDataSnapshot;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = FMLHandshakeMessage.RegistryData.class, remap = false)
public class MixinFMLHandshakeMessageRegistryData {

    @Shadow
    private ResourceLocation name;

    @ShadowConstructor
    public void RegistryData(boolean hasMore, ResourceLocation name, PersistentRegistryManager.GameDataSnapshot.Entry entry) {}

    @NewConstructor
    public void RegistryData(boolean hasMore, String name, GameDataSnapshot.Entry entry) {
        this.RegistryData(hasMore, new ResourceLocation(name), entry);
    }

    public String getName() {
        return this.name.toString();
    }
}
