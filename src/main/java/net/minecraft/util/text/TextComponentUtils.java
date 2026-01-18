package net.minecraft.util.text;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentProcessor;

public class TextComponentUtils extends ChatComponentProcessor {

    public static ITextComponent func_179985_a(ICommandSender sender, ITextComponent component, Entity entity) throws CommandException {
        return (ITextComponent) processComponent(sender, component, entity);
    }
}
