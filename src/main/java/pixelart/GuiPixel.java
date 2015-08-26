package pixelart;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiPixel extends GuiScreen {
	PixelTile pTile;
	
	public GuiPixel(PixelTile pt) {
		pTile = pt;
	}
	
	@Override
	public void initGui() {
		int sx = 140;
		int sy = 88;
		buttonList.clear();
		
		buttonList.add(new GuiButton(0, sx, sy, 20, 20, "+"));
		buttonList.add(new GuiButton(1, sx, sy + 42, 20, 20, "-"));
		
		buttonList.add(new GuiButton(2, sx + 50, sy, 20, 20, "+"));
		buttonList.add(new GuiButton(3, sx + 50, sy + 42, 20, 20, "-"));
		
		buttonList.add(new GuiButton(4, sx + 2*50, sy, 20, 20, "+"));
		buttonList.add(new GuiButton(5, sx + 2*50, sy + 42, 20, 20, "-"));
		
		
		buttonList.add(new GuiButton(6, sx + 21, sy, 23, 20, "+10"));
		buttonList.add(new GuiButton(7, sx + 21, sy + 42, 23, 20, "-10"));
		
		buttonList.add(new GuiButton(8, sx + 21 + 50, sy, 23, 20, "+10"));
		buttonList.add(new GuiButton(9, sx + 21 + 50, sy + 42, 23, 20, "-10"));
		
		buttonList.add(new GuiButton(10, sx + 21 + 2*50, sy, 23, 20, "+10"));
		buttonList.add(new GuiButton(11, sx + 21 + 2*50, sy + 42, 23, 20, "-10"));
	}
	
	@Override
	public void drawScreen(int h, int j, float f) {
		drawDefaultBackground();
		mc.renderEngine.bindTexture(new ResourceLocation("pixelart", "gui/gui.png"));
		drawTexturedModalRect(width/2 - 88, height/2 - 44, 0, 0, 256, 256);
		
		String red = Integer.toString(pTile.r);
		String green = Integer.toString(pTile.g);
		String blue = Integer.toString(pTile.b);
		int x = 162;
		int y = 115;
		fontRendererObj.drawString(red, x-fontRendererObj.getStringWidth(red)/2, y, 0);
		fontRendererObj.drawString(green, 50 + x-fontRendererObj.getStringWidth(red)/2, y, 0);
		fontRendererObj.drawString(blue, 100 + x-fontRendererObj.getStringWidth(red)/2, y, 0);
		
		super.drawScreen(h, j, f);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 0: pTile.r++; break;
			case 1: pTile.r--; break;
			case 2: pTile.g++; break;
			case 3: pTile.g--; break;
			case 4: pTile.b++; break;
			case 5: pTile.b--; break;
			
			case 6: pTile.r += 10; break;
			case 7: pTile.r -= 10; break;
			case 8: pTile.g += 10; break;
			case 9: pTile.g -= 10; break;
			case 10: pTile.b += 10; break;
			case 11: pTile.b -= 10;
			
			default: break;
		}
		
		if (pTile.r > 255 || pTile.r < 0) {
			pTile.r = Math.abs(Math.abs(pTile.r) - 255);
		}
		if (pTile.g > 255 || pTile.g < 0) {
			pTile.g = Math.abs(Math.abs(pTile.g) - 255);
		}
		if (pTile.b > 255 || pTile.b < 0) {
			pTile.b = Math.abs(Math.abs(pTile.b) - 255);
		}
		
		pTile.getWorldObj().markBlockForUpdate(pTile.xCoord, pTile.yCoord, pTile.zCoord);
	}
	
	@Override
	public void onGuiClosed() {
		int color = pTile.r | (pTile.g << 8) | (pTile.b << 16);
		ModFile.network.sendToServer(new ColorMessage(color, pTile.xCoord, pTile.yCoord, pTile.zCoord));
	}
}
