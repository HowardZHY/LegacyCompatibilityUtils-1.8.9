package space.libs.mixins.mods.legacy.tbc;

import com.superdextor.thinkbigcore.items.ItemCustomTool;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.client.ClientUtils;

import java.util.List;
import java.util.Set;

@Mixin(value = ItemCustomTool.class, remap = false)
public abstract class MixinItemCustomTool extends ItemTool {

    protected MixinItemCustomTool(float attackDamage, ToolMaterial material, Set<Block> effectiveBlocks) {
        super(attackDamage, material, effectiveBlocks);
    }

    @Dynamic
    @Shadow
    protected PotionEffect field_77785_bY;

    @Inject(method = "func_77624_a", at = @At("HEAD"), cancellable = true)
    public void func_77624_a(ItemStack stack, EntityPlayer playerIn, List<String> toolTip, boolean advanced, CallbackInfo ci) {
        ClientUtils.addEffectTooltip(toolTip, this.field_77785_bY, this, false);
        ci.cancel();
    }
}
