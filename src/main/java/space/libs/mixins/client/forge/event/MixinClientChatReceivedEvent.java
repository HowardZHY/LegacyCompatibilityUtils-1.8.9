/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge.event;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ClientChatReceivedEvent.class, remap = false)
public class MixinClientChatReceivedEvent {

    @Shadow
    public IChatComponent message;

    @Final
    @Mutable
    @Shadow
    public byte type;

    public ITextComponent getMessage()
    {
        return (ITextComponent) message;
    }

    public void setMessage(ITextComponent message)
    {
        this.message = message;
    }

    public ChatType getType() {
        return ChatType.byId(this.type);
    }
}
