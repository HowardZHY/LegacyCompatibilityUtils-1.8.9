package space.libs.mixins.mods.fixes.tbc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.client.ClientUtils;

import java.util.List;

@Pseudo
@Mixin(targets = "com.superdextor.thinkbigcore.items.ItemCustomArmor", remap = false)
public abstract class MixinItemCustomArmor extends ItemArmor {

    public MixinItemCustomArmor(ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
    }

    @Shadow
    protected @Final PotionEffect field_77785_bY;

    @Shadow
    protected @Final boolean Equipall;

    @Inject(method = "func_77624_a", at = @At("HEAD"), cancellable = true, remap = false)
    public void func_77624_a(ItemStack stack, EntityPlayer playerIn, List<String> toolTip, boolean advanced, CallbackInfo ci) {
        ClientUtils.addEffectTooltip(toolTip, this.field_77785_bY, this, true);
        ci.cancel();
    }
}
