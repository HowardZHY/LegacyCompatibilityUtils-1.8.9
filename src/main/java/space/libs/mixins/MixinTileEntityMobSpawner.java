package space.libs.mixins;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityMobSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityMobSpawner.class)
public class MixinTileEntityMobSpawner implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
