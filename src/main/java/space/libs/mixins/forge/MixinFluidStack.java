package space.libs.mixins.forge;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(value = FluidStack.class, remap = false)
public abstract class MixinFluidStack {

    /**
     * @implNote TODO: Field 2 Method Transformer
     */
    public Fluid fluid;

    @ShadowConstructor
    public void FluidStack(Fluid fluid, int amount) {}

    @ShadowConstructor
    public void FluidStack(Fluid fluid, int amount, NBTTagCompound nbt) {}

    @NewConstructor
    public void FluidStack(int fluidID, int amount) {
        FluidStack(FluidRegistry.getFluid(fluidID), amount);
    }

    @NewConstructor
    public void FluidStack(int fluidID, int amount, NBTTagCompound nbt) {
        FluidStack(FluidRegistry.getFluid(fluidID), amount, nbt);
    }

    @Inject(method = "<init>(Lnet/minecraftforge/fluids/Fluid;I)V", at = @At("RETURN"))
    public void FluidStack(Fluid fluid, int amount, CallbackInfo ci) {
        this.fluid = fluid;
    }

    @Shadow
    public @Final abstract Fluid getFluid();

    public @Final int getFluidID() {
        return FluidRegistry.getFluidID(this.getFluid());
    }
}
