package space.libs.mixins;

import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(Teleporter.class)
public class MixinTeleporter {

    @Final
    @Shadow
    private WorldServer worldServerInstance;

    public boolean func_180265_a(BlockPos pos) {
        return (!this.worldServerInstance.isAirBlock(pos) || !this.worldServerInstance.isAirBlock(pos.up()));
    }
}
