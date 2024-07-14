package space.libs.mixins;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@SuppressWarnings("unused")
@Mixin(ServerConfigurationManager.class)
public class MixinServerConfigurationManager {

    @Final
    @Mutable
    @Shadow
    public List<EntityPlayerMP> playerEntityList;

    public String func_180602_f() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.playerEntityList.size(); i++) {
            if (i > 0)
                s.append(", ");
            s.append(this.playerEntityList.get(i).getName());
        }
        return s.toString();
    }

}
