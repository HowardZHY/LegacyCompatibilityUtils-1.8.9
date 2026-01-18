/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge.event;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import org.spongepowered.asm.mixin.*;

@SuppressWarnings("unused")
@Mixin(value = EntityEvent.class, remap = false)
public abstract class MixinEntityEvent {

    @Final
    @Shadow
    public Entity entity;

    public Entity getEntity() {
        return entity;
    }
}
