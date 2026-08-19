package space.libs.mixins.tileentity;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.List;

@Mixin(TileEntityHopper.class)
public abstract class MixinTileEntityHopper implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

    @SuppressWarnings("SizeReplaceableByIsEmpty")
    @Public
    private static EntityItem func_145897_a(World worldIn, double x, double y, double z) {
        List<EntityItem> list = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D), IEntitySelector.selectAnything);
        return (list.size() > 0) ? list.get(0) : null;
    }
}
