package space.libs.mixins;

import alexiil.mods.civ.event.VanillaEventHooks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import space.libs.util.ModDetector;

@SuppressWarnings("unused")
@Mixin(ContainerPlayer.class)
public abstract class MixinContainerPlayer {

    @Shadow
    public InventoryCrafting craftMatrix;

    @Shadow
    public IInventory craftResult;

    @Shadow
    private @Final @Mutable EntityPlayer thePlayer;

    public void func_75130_a(IInventory inventoryIn, int dummy) {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.thePlayer.worldObj));
    }

    @Redirect(method = "onCraftMatrixChanged(Lnet/minecraft/inventory/IInventory;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/crafting/CraftingManager;findMatchingRecipe(Lnet/minecraft/inventory/InventoryCrafting;Lnet/minecraft/world/World;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack onCraftMatrixChanged(CraftingManager manager, InventoryCrafting ic, World world) {
        if (ModDetector.hasCivCraft) {
            return VanillaEventHooks.canCraftPlayerEvent(this.craftMatrix, this.thePlayer);
        }
        return manager.findMatchingRecipe(ic, world);
    }
}
