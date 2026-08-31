/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.b3d.B3DLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings({"deprecation", "unused"})
@Mixin(value = B3DLoader.Wrapper.class, remap = false)
public abstract class MixinB3DLoaderWrapper {

    @Shadow(prefix = "original$")
    public abstract IModelState original$getDefaultState();

    public B3DLoader.B3DState getDefaultState() {
        return (B3DLoader.B3DState)original$getDefaultState();
    }
}
