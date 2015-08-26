package pixelart;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
	
public class PixelTile extends TileEntity {
	public int r = 255;
	public int g = 255;
	public int b = 255;
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
	    super.readFromNBT(tagCompound);
	    int color = tagCompound.getInteger("col");
	    r = color & 0xFF;
	    g = (color >> 8) & 0xFF;
	    b = (color >> 16) & 0xFF;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
	    super.writeToNBT(tagCompound);
	    
	    int color = r | (g << 8) | (b << 16);
	    tagCompound.setInteger("col", color);
	}
	
	@Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        int color = r | (g << 8) | (b << 16);
        nbtTag.setInteger("col", color);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbtTag = packet.func_148857_g();
        int color = nbtTag.getInteger("col");
        r = color & 0xFF;
        g = (color >> 8) & 0xFF;
        b = (color >> 16) & 0xFF;
    }
    
    @Override
    public boolean canUpdate() {
    	return false;
    }
}