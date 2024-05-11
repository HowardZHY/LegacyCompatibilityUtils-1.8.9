/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.client.settings;

@SuppressWarnings("unused")
public interface IKeyConflictContext {
    boolean isActive();

    boolean conflicts(IKeyConflictContext paramIKeyConflictContext);
}
