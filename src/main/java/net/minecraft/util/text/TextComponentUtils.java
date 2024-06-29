package net.minecraft.util.text;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.IChatComponent;

public class TextComponentUtils extends ChatComponentProcessor {
    public static ITextComponent func_179985_a(ICommandSender p_179985_0_, ITextComponent p_179985_1_, Entity p_179985_2_) throws CommandException {
        return (ITextComponent) processComponent(p_179985_0_, (IChatComponent) p_179985_1_, p_179985_2_);
    }
}
