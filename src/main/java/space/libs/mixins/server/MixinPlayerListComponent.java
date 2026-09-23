package space.libs.mixins.server;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.server.gui.PlayerListComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerListComponent.class)
public abstract class MixinPlayerListComponent implements IUpdatePlayerListBox {

    @Shadow
    public void update() {}

}
