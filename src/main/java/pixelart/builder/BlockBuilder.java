package pixelart.builder;

import pixelart.ModFile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBuilder extends Block {
	public BlockBuilder() {
		super(Material.iron);
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockTextureName("pixelart:white");
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(5);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.isSneaking()) return false;
		
		par5EntityPlayer.openGui(ModFile.instance, 1, par1World, par2, par3, par4);
		return true;
	}
}
