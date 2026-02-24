package space.libs.util.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModelResourceLocationImpl extends DefaultStateMapper implements ItemMeshDefinition {

    public final ModelResourceLocation location;

    public static ModelResourceLocationImpl from(final Block block) {
        ResourceLocation rl = block.delegate.getResourceName();
        ModelResourceLocation mrl = new ModelResourceLocation(rl, rl.getResourcePath());
        return new ModelResourceLocationImpl(mrl);
    }

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
