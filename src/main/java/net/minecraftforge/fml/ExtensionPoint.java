/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.fml;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;

public class ExtensionPoint<T>
{
    public static final ExtensionPoint<BiFunction<Minecraft, Screen, Screen>> CONFIGGUIFACTORY = new ExtensionPoint<>();

    public static final ExtensionPoint<Pair<Supplier<String>, BiPredicate<String, Boolean>>> DISPLAYTEST = new ExtensionPoint<>();

    private Class<T> type;

    public ExtensionPoint() {}

}
