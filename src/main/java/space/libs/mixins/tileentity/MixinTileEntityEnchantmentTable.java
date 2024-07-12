package space.libs.mixins.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityEnchantmentTable.class)
public class MixinTileEntityEnchantmentTable implements IUpdatePlayerListBox {

    @Override
    @Shadow
    public void update() {}

}
