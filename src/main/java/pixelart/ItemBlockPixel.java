package pixelart;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemBlockPixel extends ItemBlock {
	public ItemBlockPixel(Block b) {
		super(b);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		TileEntity tte = par3World.getTileEntity(par4, par5, par6);
		super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		
		if (tte == null) return true;
		if (!(tte instanceof PixelTile)) return true;
		
		PixelTile pt = (PixelTile)tte;
		
		if (par7 == 0)
        {
            --par5;
        }

        if (par7 == 1)
        {
            ++par5;
        }

        if (par7 == 2)
        {
            --par6;
        }

        if (par7 == 3)
        {
            ++par6;
        }

        if (par7 == 4)
        {
            --par4;
        }

        if (par7 == 5)
        {
            ++par4;
        }
        
        PixelTile ptc = (PixelTile)par3World.getTileEntity(par4, par5, par6);
        int color = pt.r | (pt.g << 8) | (pt.b << 16);
        ptc.r = pt.r;
        ptc.g = pt.g;
        ptc.b = pt.b;
		ModFile.network.sendToServer(new ColorMessage(color, ptc.xCoord, ptc.yCoord, ptc.zCoord));
		par3World.markBlockForUpdate(pt.xCoord, pt.yCoord, pt.zCoord);
		
        return true;
	}
}
