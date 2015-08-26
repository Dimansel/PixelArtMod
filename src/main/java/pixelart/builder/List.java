package pixelart.builder;

import java.util.ArrayList;

import net.minecraft.client.gui.FontRenderer;

public class List {
	public ArrayList<ListItem> items;
	private int x;
	private int y;
	public ListItem selected = null;
	
	public List(int x, int y) {
		items = new ArrayList<ListItem>();
		this.x = x;
		this.y = y;
	}
	
	public void AddItem(String str) {
		int last = items.size();
		items.add(new ListItem(str, x, y+last*10, 60));
	}
	
	public void render(FontRenderer fr) {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(fr);
		}
	}
}
