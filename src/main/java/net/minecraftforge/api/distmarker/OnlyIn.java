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
 * Marks the associated element as being only available on a certain {@link Dist}.
 *
 * Classes, fields, methods and constructors can be marked as only available in a specific distribution
 * based on the presence of this annotation.
 *
 *
 * <p>This is generally meant for internal Forge and FML use only
 * and modders should avoid its use whenever possible.</p>
 *
 *
 * Note, this will <em>only</em> apply to the direct element marked. This code:
 * {@code @OnlyIn(Dist.CLIENT) public MyField field = new MyField();} will <strong>not</strong> work,
 * as the initializer is a separate piece of code to the actual field declaration, and will not be able to find
 * its field on the wrong side.
 *
 * When applied on a package, this only applies to the package class file itself.
 * It is a reasonable assumption that the whole package will also be restricted to that distribution, but not a requirement.
 *
 * When applied on an annotation, this only applies to the annotation class itself, not any members that are annotated with it.
 */
@SuppressWarnings("all")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PACKAGE, ElementType.ANNOTATION_TYPE})
public @interface OnlyIn
{
    public Dist value();

    /**
     * Only valid on Type definitions.
     * Marks an interface the class implements as only being available on the specific distribution.
     * This <em>does not</em> propagate down to that interfaces methods, as the class doesn't exist so it's methods can't be determined.
     */
    public Class<?> _interface() default Object.class;
}
