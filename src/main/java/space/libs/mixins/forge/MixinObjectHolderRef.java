/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import java.lang.reflect.Field;

@SuppressWarnings("all")
@Mixin(targets = "net.minecraftforge.fml.common.registry.ObjectHolderRef", remap = false)
public abstract class MixinObjectHolderRef {

    @ShadowConstructor
    void ObjectHolderRef(Field field, ResourceLocation injectedObject, boolean extractFromExistingValues) {}

    @NewConstructor
    public void ObjectHolderRef(Field field, String injectedObject, boolean extractFromExistingValues) {
        ObjectHolderRef(field, new ResourceLocation(injectedObject), extractFromExistingValues);
    }
}
