/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge.event;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = FMLMissingMappingsEvent.MissingMapping.class, remap = false)
public class MixinFMLMissingMappingsEvent {

    @Shadow
    private FMLMissingMappingsEvent.Action action;

    @ShadowConstructor
    public void MissingMapping(FMLMissingMappingsEvent outer, GameRegistry.Type type, ResourceLocation name, int id) {}

    @NewConstructor
    public void MissingMapping(FMLMissingMappingsEvent outer, GameRegistry.Type type, String name, int id) {
        MissingMapping(outer, type, new ResourceLocation(name), id);
    }

    public void setAction(FMLMissingMappingsEvent.Action action) {
        this.action = action;
    }
}
