/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IntHashMap;
import net.minecraftforge.client.settings.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.interfaces.IKeyBinding;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.Public;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = KeyBinding.class, priority = 100)
public abstract class MixinKeyBinding implements IKeyBinding {

    @SuppressWarnings("all")
    @Final
    @Mutable
    @Shadow
    private static IntHashMap<KeyBinding> hash;

    @Shadow
    private int keyCode;

    @Shadow
    public abstract int getKeyCode();

    @Shadow
    public abstract boolean isPressed();

    @ShadowConstructor
    public void KeyBinding(String description, int keyCode, String category) {}

    @NewConstructor
    public void KeyBinding(String description, int keyCode) {
        this.KeyBinding(description, keyCode, "key.categories.gameplay");
    }

    @NewConstructor
    public void KeyBinding(String description, IKeyConflictContext keyConflictContext, int keyCode, String category) {
        this.KeyBinding(description, keyConflictContext, KeyModifier.NONE, keyCode, category);
    }

    @NewConstructor
    public void KeyBinding(String description, IKeyConflictContext keyConflictContext, KeyModifier keyModifier, int keyCode, String category) {
        this.KeyBinding(description, keyCode, category);
        this.keyConflictContext = keyConflictContext;
        this.keyModifier = keyModifier;
        this.keyModifierDefault = keyModifier;
        if (keyModifier != KeyModifier.NONE) {
            setAllowsKeyModifiers();
        }
    }

    private net.minecraftforge.client.settings.KeyModifier keyModifierDefault = net.minecraftforge.client.settings.KeyModifier.NONE;

    private net.minecraftforge.client.settings.KeyModifier keyModifier = net.minecraftforge.client.settings.KeyModifier.NONE;

    private net.minecraftforge.client.settings.IKeyConflictContext keyConflictContext = net.minecraftforge.client.settings.KeyConflictContext.UNIVERSAL;

    private boolean allowsKeyModifiers = false;

    public void setAllowsKeyModifiers() {
        this.allowsKeyModifiers = true;
    }

    /**
     * Checks that the key conflict context and modifier are active, and that the keyCode matches this binding.
     */
    public boolean isActiveAndMatches(int keyCode) {
        return keyCode == this.getKeyCode() && getKeyConflictContext().isActive() && (!allowsKeyModifiers || getKeyModifier().isActive());
    }

    public void setKeyConflictContext(net.minecraftforge.client.settings.IKeyConflictContext keyConflictContext) {
        this.keyConflictContext = keyConflictContext;
    }

    public net.minecraftforge.client.settings.IKeyConflictContext getKeyConflictContext()
    {
        return keyConflictContext;
    }

    public net.minecraftforge.client.settings.KeyModifier getKeyModifierDefault()
    {
        return keyModifierDefault;
    }

    public net.minecraftforge.client.settings.KeyModifier getKeyModifier() {
        if (allowsKeyModifiers) {
            return keyModifier;
        } else {
            return net.minecraftforge.client.settings.KeyModifier.NONE;
        }
    }

    public void setKeyModifier(net.minecraftforge.client.settings.KeyModifier keyModifier) {
        hashmap.removeKey((KeyBinding) (Object) this);
        this.keyModifier = keyModifier;
        hashmap.addKey(keyCode, (KeyBinding) (Object) this);
    }

    public boolean conflicts(KeyBinding other) {
        IKeyBinding accessor = (IKeyBinding) other;
        if (getKeyConflictContext().conflicts(accessor.getKeyConflictContext()) || accessor.getKeyConflictContext().conflicts(getKeyConflictContext())) {
            if (!allowsKeyModifiers || !accessor.allowsKeyModifiers() || getKeyModifier() == accessor.getKeyModifier()) {
                return getKeyCode() == other.getKeyCode();
            }
        }
        return false;
    }

    public boolean allowsKeyModifiers() {
        return this.allowsKeyModifiers;
    }

    @Public
    private static net.minecraftforge.client.settings.KeyBindingMap hashmap;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void setHash(CallbackInfo ci) {
        hashmap = new net.minecraftforge.client.settings.KeyBindingMap();
    }

    public boolean func_74509_c() {
        return this.isPressed();
    }
}
