package space.libs.mixins;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(ItemBlock.class)
public abstract class MixinItemBlock {

    @Shadow
    public static boolean setTileEntityNBT(World worldIn, EntityPlayer player, BlockPos pos, ItemStack stack) {
        throw new AbstractMethodError();
    }

    @Public
    private static boolean func_179224_a(World worldIn, BlockPos pos, ItemStack stack) {
        return setTileEntityNBT(worldIn, pos, stack, (EntityPlayer) null);
    }

    @Public
    private static boolean setTileEntityNBT(World worldIn, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return setTileEntityNBT(worldIn, player, pos, stack);
    }
}
