package data;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class UI {
	private TrueTypeFont font;
	private Font awtFont;
	
	public UI() {
		awtFont = new Font("Times New Roman", Font.BOLD, 16);
		font = new TrueTypeFont(awtFont, false);
	}
	
	public void drawText(int x, int y, String text) {
		font.drawString(x, y, text);
	}
	
	public void drawText(int x, int y, int number) {
		font.drawString(x, y, Integer.toString(number));
	}
}
