package pixelart;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pixelart.builder.GuiBuilder;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(x, y, z);
		/*switch(id)
		{
			case 0: return new ContainerBox(player.inventory, (TileEntityBox) tile_entity);
		}*/
		return null;
	}
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(x, y, z);
		
		if (tile_entity instanceof PixelTile) {
			return new GuiPixel((PixelTile)tile_entity);
		}
		else {
			return new GuiBuilder();
		}
	}
}