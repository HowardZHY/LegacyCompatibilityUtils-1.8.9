package space.libs.mixins.mods.fixes.tbc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.client.ClientUtils;

import java.util.List;

@Pseudo
@Mixin(
    targets = {
        "com.superdextor.thinkbigcore.items.ItemCustomAxe",
        "com.superdextor.thinkbigcore.items.ItemCustomPickaxe",
        "com.superdextor.thinkbigcore.items.ItemCustomShovel",
        "com.superdextor.thinkbigcore.items.ItemCustomSword"
    },
    remap = false)
public abstract class MixinItemCustomTools {

    @Shadow(remap = false)
    protected PotionEffect field_77785_bY;

    @Inject(method = "func_77624_a", at = @At("HEAD"), cancellable = true)
    public void func_77624_a(ItemStack stack, EntityPlayer playerIn, List<String> toolTip, boolean advanced, CallbackInfo ci) {
        ClientUtils.addEffectTooltip(toolTip, this.field_77785_bY, (Item) (Object) this, false);
        ci.cancel();
    }
}
