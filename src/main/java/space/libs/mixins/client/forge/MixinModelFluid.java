/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ModelFluid.class, remap = false)
public abstract class MixinModelFluid implements IModelPart {}
