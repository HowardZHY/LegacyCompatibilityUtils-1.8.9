package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

@SuppressWarnings("unused")
public class BlockCompressed extends Block {

    /** mapColor */
    private final MapColor field_150202_a;

    public BlockCompressed(MapColor var1) {
        super(Material.iron);
        this.field_150202_a = var1;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /** getMapColor */
    public MapColor func_180659_g(IBlockState var1) {
        return this.field_150202_a;
    }
}