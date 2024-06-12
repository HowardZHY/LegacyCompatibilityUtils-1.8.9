/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(value = MinecraftForge.class, remap = false)
public class MixinMinecraftForge {
    @Public
    private static final IEventBus EVENT_BUS = (IEventBus) new EventBus();
}
