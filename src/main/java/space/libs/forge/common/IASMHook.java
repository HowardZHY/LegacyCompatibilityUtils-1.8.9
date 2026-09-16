/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 *
 * Contributors:
 *     cpw - implementation
 */

package space.libs.forge.common;

import org.objectweb.asm.tree.ClassNode;

public @Deprecated interface IASMHook {
    /**
     * Inject the Mod class node into this instance. This allows retrieval from custom
     * attributes or other artifacts in your mod class
     *
     * @param modClassNode The mod class
     * @return optionally some code generated classes that will be injected into the classloader
     */
    ClassNode[] inject(ClassNode modClassNode);
    /**
     * Allow mods to manipulate classes loaded from this Mod's jar file. The Mod
     * class is always guaranteed to be called first.
     * The node state should be changed in place.
     *
     * @param node The class being loaded
     */
    void modifyClass(String className, ClassNode node);
}
