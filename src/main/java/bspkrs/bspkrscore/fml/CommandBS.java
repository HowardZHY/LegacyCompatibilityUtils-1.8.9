package bspkrs.bspkrscore.fml;

import net.minecraft.command.*;

import java.util.*;

/**
 * @apiNote Dummy Class
 */
@SuppressWarnings("unused")
public class CommandBS extends CommandBase {

    public static List<String> version = new ArrayList<>();

    @Override
    public String getCommandName() {
        return "bs";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "bspkrsCore Dummy Command";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws WrongUsageException {
        throw new WrongUsageException("Dummy Command");
    }

    static {
        version.add("version");
    }
}
