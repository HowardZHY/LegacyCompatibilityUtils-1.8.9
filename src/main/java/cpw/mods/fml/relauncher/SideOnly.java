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

package cpw.mods.fml.relauncher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks the associated element as being only available on a certain {@link Side}. This is
 * generally meant for internal Forge and FML use only and should only be used on mod classes
 * when other more common mechanisms, such as using a SidedProxy fail to work.
 * Note, this will <em>only</em> apply to the direct element marked. This code:
 * <code> @SideOnly public MyField field = new MyField();</code> will <strong>not</strong> work, as the initializer
 * is a separate piece of code to the actual field declaration, and will not be able to find
 * its field on the wrong side.
 *
 * @author cpw
 *
 */
@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface SideOnly
{
    Side value();
}
