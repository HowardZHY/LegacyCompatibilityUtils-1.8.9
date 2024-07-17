package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCompressed extends Block {

    /** mapColor */
    public MapColor field_150202_a;

    public BlockCompressed(MapColor color) {
        super(Material.iron, color);
        this.field_150202_a = color;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public BlockCompressed(Material material, MapColor color)
    {
        super(material, color);
        this.field_150202_a = color;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /** getMapColor */
    public MapColor func_180659_g(IBlockState color) {
        return this.field_150202_a;
    }
}
