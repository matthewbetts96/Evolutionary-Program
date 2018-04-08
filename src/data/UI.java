package data;

import java.awt.Font;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import helpers.Clock;
import helpers.Config;

public class UI {
	private TrueTypeFont font;
	private Font awtFont;
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
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
	
	public static void updateStats(PrintWriter pw, TileGrid grid, UI gameUI) {
		
		//Get mouse position
		int mouseX = Mouse.getX(); 
		int mouseY = 999 - Mouse.getY();
		
		//get the size of the entity list 
		int entNum = Spawn.getEntityList().size();
		
		Tile tile = grid.GetTile(((int)mouseX/Config.getSize()), ((int)mouseY/Config.getSize()));
		
		gameUI.drawText(850, 10, "Tick(s) = " + Clock.ticksSinceGameStart());
		
		gameUI.drawText(850, 30, "X :" + mouseX);
		gameUI.drawText(850, 50, "Y :" + mouseY);
		gameUI.drawText(850, 70, "Tile Type :" + tile.getType().getTextureName());
		gameUI.drawText(850, 90, "Food :" + tile.getCurrentFood());
		gameUI.drawText(850, 110, "Food Regen :" + tile.getFoodRegen());
		
		gameUI.drawText(820, 150, "Average Attack = " + df2.format(creatureAI.getCumulativeAttack()/entNum));
		gameUI.drawText(820, 170, "Average Def = " + df2.format(creatureAI.getCumulativeDef()/entNum));
		gameUI.drawText(820, 190, "Average Speed = " + df2.format(creatureAI.getCumulativeSpeed()/entNum));
		gameUI.drawText(820, 210, "Average Int = " + df2.format(creatureAI.getCumulativeInt()/entNum));
		
		gameUI.drawText(820, 250, "Adult Male = " + df2.format(creatureAI.getAdultMaleNumber()));
		gameUI.drawText(820, 270, "Adult Female = " + df2.format(creatureAI.getAdultFemaleNumber()));
		gameUI.drawText(820, 290, "Child Male = " + df2.format(creatureAI.getChildMaleNumber()));
		gameUI.drawText(820, 310, "Child Female = " + df2.format(creatureAI.getChildFemaleNumber()));
		
		
		pw.println(df2.format(creatureAI.getCumulativeAttack()/entNum) + "," 
				+ df2.format(creatureAI.getCumulativeDef()/entNum) + "," 
				+ df2.format(creatureAI.getCumulativeSpeed()/entNum) + "," 
				+ df2.format(creatureAI.getCumulativeInt()/entNum) + "," 
				+ df2.format(Spawn.getEntityList().size()) + "," 
				+ df2.format(creatureAI.getAdultMaleNumber()) + "," 
				+ df2.format(creatureAI.getAdultFemaleNumber()) + ","
				+ df2.format(creatureAI.getChildMaleNumber()) + ","
				+ df2.format(creatureAI.getChildFemaleNumber())
				);
		
		
		creatureAI.setCumulativeAttack(0);
		creatureAI.setCumulativeDef(0);
		creatureAI.setCumulativeSpeed(0);
		creatureAI.setCumulativeInt(0);
		creatureAI.setAdultMaleNumber(0);
		creatureAI.setAdultFemaleNumber(0);
		creatureAI.setChildMaleNumber(0);
		creatureAI.setChildFemaleNumber(0);
		
	}
}

