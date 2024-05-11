/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = EntityEvent.class, remap = false)
public class MixinEntityEvent {

    @Final
    @Mutable
    @Shadow
    public Entity entity;

    public Entity getEntity()
    {
        return entity;
    }
}
