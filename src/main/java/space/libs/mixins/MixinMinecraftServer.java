package space.libs.mixins;

import net.minecraft.command.ICommandManager;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.ITickable;
import org.spongepowered.asm.mixin.*;
import space.libs.util.MappedName;

import java.util.List;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {

    @Shadow
    private @Final List<ITickable> playersOnline;

    @Shadow
    protected @Final ICommandManager commandManager;

    @MappedName("handleRConCommand")
    public String func_71252_i(String command) {
        RConConsoleSource.getInstance().resetLog();
        this.commandManager.executeCommand(RConConsoleSource.getInstance(), command);
        return RConConsoleSource.getInstance().getLogContents();
    }

    @MappedName("getPlugins")
    public String func_71258_A() {
        return "";
    }

    @MappedName("registerTickable")
    public void func_82010_a(IUpdatePlayerListBox tickable) {
        this.playersOnline.add(tickable);
    }
}
