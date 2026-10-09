package space.libs.mixins.world;

import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.*;

@SuppressWarnings("unused")
@Mixin(Teleporter.class)
public class MixinTeleporter {

    @Shadow
    private @Final WorldServer worldServerInstance;

    public boolean func_180265_a(BlockPos pos) {
        return (!this.worldServerInstance.isAirBlock(pos) || !this.worldServerInstance.isAirBlock(pos.up()));
    }
}
