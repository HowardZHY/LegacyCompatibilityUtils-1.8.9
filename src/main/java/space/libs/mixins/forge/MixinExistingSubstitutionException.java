/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = ExistingSubstitutionException.class, remap = false)
public class MixinExistingSubstitutionException {

    @ShadowConstructor
    public void ExistingSubstitutionException(ResourceLocation fromName, Object toReplace) {}

    @NewConstructor
    public void ExistingSubstitutionException(String name, Object toReplace) {
        this.ExistingSubstitutionException(new ResourceLocation(name), toReplace);
    }
}
