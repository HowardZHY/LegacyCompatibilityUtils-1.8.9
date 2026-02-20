package space.libs.util.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class ModelResourceLocationImpl extends DefaultStateMapper implements ItemMeshDefinition {

    public final ModelResourceLocation location;

    public ModelResourceLocationImpl(final ModelResourceLocation location) {
        this.location = location;
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        return this.location;
    }

    @Override
    public ModelResourceLocation getModelResourceLocation(IBlockState state) {
        return this.location;
    }
}
