package pixelart;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PixelBlock extends Block implements ITileEntityProvider {
	public static int renderID = 0;
	
	public PixelBlock() {
		super(Material.iron);
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockTextureName("pixelart:white");
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(5);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.isSneaking()) return false;
		
		par5EntityPlayer.openGui(ModFile.instance, 0, par1World, par2, par3, par4);
		return true;
	}
	
	@Override
	public int getRenderType() {
		return renderID;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new PixelTile();
	}
}
