package pixelart.builder;

import java.io.File;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiBuilder extends GuiScreen {
	private List lst;
	private OpenWindow ow;
	
	public GuiBuilder() {
		lst = new List(100, 50);
		
		File[] roots = File.listRoots();
		for (int a = 0; a < roots.length; a++)
			lst.AddItem(roots[a].getPath());
		
		ow = new OpenWindow();
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
		
		lst.render(fontRendererObj);
		
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
			System.out.println(file.getAbsolutePath());
		}
	}
}
