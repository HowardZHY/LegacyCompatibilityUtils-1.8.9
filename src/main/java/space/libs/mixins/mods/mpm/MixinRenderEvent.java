package space.libs.mixins.mods.mpm;

import net.minecraft.entity.player.EntityPlayer;
import noppes.mpm.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = "noppes.mpm.client.RenderEvent", remap = false)
public class MixinRenderEvent {

    /**
     * @author HowardZHY
     * @reason Remove the Lag when loading player skins
     * */
    @Overwrite
    public void loadPlayerResource(EntityPlayer player, ModelData data) {}

}
