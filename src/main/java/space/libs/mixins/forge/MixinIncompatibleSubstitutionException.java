/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraftforge.fml.common.registry.IncompatibleSubstitutionException;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowSuperConstructor;

@SuppressWarnings("all")
@Mixin(IncompatibleSubstitutionException.class)
public class MixinIncompatibleSubstitutionException {

    @ShadowSuperConstructor
    public void RuntimeException(String message) {}

    @NewConstructor
    public void IncompatibleSubstitutionException(String name, Object replacement, Object original) {
        this.RuntimeException(String.format("The substitute %s for %s (type %s) is type incompatible.", replacement.getClass().getName(), name, original.getClass().getName()));
    }

}
