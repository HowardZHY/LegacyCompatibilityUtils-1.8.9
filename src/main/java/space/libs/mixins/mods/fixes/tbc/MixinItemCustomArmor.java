package space.libs.mixins.mods.fixes.tbc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.*;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
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

    /**
     * @author HowardZHY
     * @reason Fix Crash for substring after I18N
     */
    @SideOnly(Side.CLIENT)
    @Overwrite
    public void func_77624_a(ItemStack stack, EntityPlayer playerIn, List<String> toolTip, boolean advanced) {
        if (this.field_77785_bY != null) {
            EnumChatFormatting prefix;
            if (Potion.potionTypes[this.field_77785_bY.getPotionID()].isBadEffect()) {
                prefix = EnumChatFormatting.RED;
            } else {
                prefix = EnumChatFormatting.GRAY;
            }
            toolTip.add(" ");
            if (this.Equipall) {
                toolTip.add("When Full-set Worn:");
            } else {
                toolTip.add("When Worn:");
            }
            toolTip.add("Effect As: " + prefix + StatCollector.translateToLocal(this.field_77785_bY.getEffectName() + ".postfix")
                + " " + StatCollector.translateToLocal("potion.potency." + this.field_77785_bY.getAmplifier()));
        }
        toolTip.add(" ");
        toolTip.add(EnumChatFormatting.BLUE + "+" + this.getArmorMaterial().getDamageReductionAmount(this.armorType) + " Defence");
    }
}
