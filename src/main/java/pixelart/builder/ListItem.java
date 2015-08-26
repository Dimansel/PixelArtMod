package pixelart.builder;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class ListItem {
	public String str;
	private int x;
	private int y;
	private int width;
	public boolean selected = false;
	
	public ListItem(String str, int x, int y, int width) {
		this.str = str;
		this.x = x;
		this.y = y;
		this.width = width;
	}
	
	public void render(FontRenderer fr) {
		int color = 0;
		if (selected) color = 0xFF505050;
		else color = 0xFF909090;
		
		GuiScreen.drawRect(x, y, x+width, y+10, color);
		drawEmptyRect(x, y, width, 10);
		fr.drawString(str, x+2, y+1, 0);
	}
	
	public void drawEmptyRect(int x1, int y1, int w, int h) {
		drawLine(x1, y1, x1 + w, y1);
		drawLine(x1, y1+h, x1+w, y1+h);
		drawLine(x1, y1, x1, y1+h);
		drawLine(x1+w, y1, x1+w, y1+h);
	}
	
	public void drawLine(int x1, int y1, int x2, int y2) {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        	
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
	}
	
	public boolean mousePressed(Minecraft mc, int cx, int cy) {
        return cx >= x && cy >= y && cx < x + width && cy < y + 10;
    }
}
