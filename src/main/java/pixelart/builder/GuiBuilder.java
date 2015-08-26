package pixelart.builder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import pixelart.PixelTile;
import pixelart.sync.PacketSender;

public class GuiBuilder extends GuiScreen {
	private List lst;
	private OpenWindow ow;
	private int x;
	private int y;
	private int z;
	private EntityPlayer player;
	
	public GuiBuilder(int x, int y, int z, EntityPlayer player) {
		lst = new List(100, 50);
		
		File[] roots = File.listRoots();
		for (int a = 0; a < roots.length; a++)
			lst.AddItem(roots[a].getPath());
		
		ow = new OpenWindow();
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
	}
	
	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(new GuiButton(0, 170, 150, 50, 20, "Go"));
	}
	
	@Override
	public void drawScreen(int h, int j, float f) {
		drawDefaultBackground();
		mc.renderEngine.bindTexture(new ResourceLocation("pixelart", "gui/gui2.png"));
		drawTexturedModalRect(width/2 - 128, height/2 - 82, 0, 0, 256, 256);
		
		lst.render(fontRenderer);
		
		super.drawScreen(h, j, f);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 0: openFile();
			default: break;
		}
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		
		lst.selected = null;
		for (int a = 0; a < lst.items.size(); a++)
			lst.items.get(a).selected = false;
		
		for (int i = 0; i < lst.items.size(); i++) {
			ListItem li = lst.items.get(i);
			
			if (li.mousePressed(mc, par1, par2)) {
				li.selected = true;
				lst.selected = li;
			}
		}
	}
	
	private void select() {
		if (lst.selected != null) {
			File f = new File(lst.selected.str);
			String[] cats = f.list();
			
			lst.items.clear();
			for (int a = 0; a < cats.length; a++)
				lst.AddItem(cats[a]);
		}
	}
	
	private void openFile() {
		File file = ow.runWindow();
		if (file != null) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (img == null) return;
			
			int iw = img.getWidth();
			int ih = img.getHeight();
			
			System.out.println(iw);
			System.out.println(ih);
			
			int curx = x-iw/2;
			int cury = y;
			int curz = z - 5;
			
			for (int y1 = 0; y1 < ih; y1++) {
				for (int x1 = 0; x1 < iw; x1++) {
					player.worldObj.setBlock(curx, cury, curz, 4000, 0, 1);
					System.out.println(Integer.toString(curx) + "; " + Integer.toString(cury));
					PixelTile pt = (PixelTile)player.worldObj.getBlockTileEntity(curx, cury, curz);
					int color = img.getRGB(x1, y1);
					int r = color & 0xFF;
			        int g = (color >> 8) & 0xFF;
			        int b = (color >> 16) & 0xFF;
			        
			        /*pt.r = r;
			        pt.g = g;
			        pt.b = b;
			        PacketSender.sendChangeToServer(r, g, b, curx, cury, curz);*/
			        
			        curx++;
				}
				
				curx = x-iw/2;
				cury++;
			}
		}
	}
}
