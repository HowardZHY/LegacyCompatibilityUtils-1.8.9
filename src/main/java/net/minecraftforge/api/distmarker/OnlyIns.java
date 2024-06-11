/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.api.distmarker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Java 8 Repeatable container for the {@link OnlyIn} annotation.
 * Only usable on type definitions, and only meaningful when interface value is specified in {@link OnlyIn#_interface()}
 */
@SuppressWarnings("all")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OnlyIns {
    OnlyIn[] value();
}
