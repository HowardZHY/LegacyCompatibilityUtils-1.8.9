package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityMobSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityMobSpawner.class)
public abstract class MixinTileEntityMobSpawner implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

}
