/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.fml.common;

import net.minecraftforge.fml.relauncher.Side;

import java.lang.annotation.*;

@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventBusSubscriber {

    /**
     * A class which will be subscribed to net.minecraftforge.common.MinecraftForge.EVENT_BUS at mod construction time.
     */
    Side[] value() default { Side.CLIENT, Side.SERVER };

    /**
     * Optional value, only necessary if this annotation is not on the same class that has a @Mod annotation.
     * Needed to prevent early classloading of classes not owned by your mod.
     */
    String modid() default "";

}
