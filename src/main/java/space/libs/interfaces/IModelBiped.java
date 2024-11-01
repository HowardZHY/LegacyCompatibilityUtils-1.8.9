package space.libs.interfaces;

import net.minecraft.client.model.EnumArmPose;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public interface IModelBiped {

    void func_187073_a(float scale, EnumHandSide side);

    EnumArmPose getRightArmPose();

    void setRightArmPose(EnumArmPose pose);

}
