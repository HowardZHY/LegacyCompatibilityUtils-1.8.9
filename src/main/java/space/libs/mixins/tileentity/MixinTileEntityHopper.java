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

@SuppressWarnings("all")
@Mixin(TileEntityHopper.class)
public abstract class MixinTileEntityHopper implements IUpdatePlayerListBox {

    @Shadow
    public abstract void update();

    @Public
    private static EntityItem func_145897_a(World worldIn, double p_145897_1_, double p_145897_3_, double p_145897_5_) {
        List<EntityItem> list = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(p_145897_1_, p_145897_3_, p_145897_5_, p_145897_1_ + 1.0D, p_145897_3_ + 1.0D, p_145897_5_ + 1.0D), IEntitySelector.selectAnything);
        return (list.size() > 0) ? list.get(0) : null;
    }
}
