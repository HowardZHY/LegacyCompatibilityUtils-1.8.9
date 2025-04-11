/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = ConfigChangedEvent.class, remap = false)
public class MixinConfigChangedEvent {

    @Shadow
    public @Final String modID;

    @Shadow
    public @Final boolean isWorldRunning;

    @Shadow
    public @Final boolean requiresMcRestart;

    @Shadow
    public @Final String configID;

    /**
     * The Mod ID of the mod whose configuration just changed.
     */
    public String getModID() {
        return modID;
    }

    /**
     * Whether a world is currently running.
     */
    public boolean isWorldRunning() {
        return isWorldRunning;
    }

    /**
     * Will be set to true if any elements were changed that require a restart of Minecraft.
     */
    public boolean isRequiresMcRestart() {
        return requiresMcRestart;
    }

    /**
     * A String identifier for this ConfigChangedEvent.
     */
    public String getConfigID() {
        return configID;
    }
}
