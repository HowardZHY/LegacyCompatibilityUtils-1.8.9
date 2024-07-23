package space.libs.mixins.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase {

    public MixinEntityPlayer(World worldIn) {
        super(worldIn);
    }

    @Shadow
    public abstract ItemStack getHeldItem();

    @Override
    public ItemStack func_184586_b(EnumHand hand) {
        return this.getHeldItem();
    }

    /** isHandActive */
    public boolean func_184587_cr() {
        return (this.getHeldItem() != null);
    }

    @Override
    public EnumHandSide func_184591_cq() {
        return EnumHandSide.RIGHT;
    }
}
