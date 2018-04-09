package data;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	

	private static PrintWriter daypw = null; 
	private static PrintWriter monthpw = null;
	private static PrintWriter yearpw = null;

	
	public UI() throws IOException {
		dayWriter();
		monthWriter();
		yearWriter();
		awtFont = new Font("Times New Roman", Font.BOLD, 16);
		font = new TrueTypeFont(awtFont, false);
	}
	
	public void drawText(int x, int y, String text) {
		font.drawString(x, y, text);
	}
	
	public void drawText(int x, int y, int number) {
		font.drawString(x, y, Integer.toString(number));
	}
	
	public static void updateStats(TileGrid grid, UI gameUI) throws IOException {
		
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
		
		//always write everyday for a year
		daypw.println(Clock.ticksSinceGameStart() + "," 
				+ df2.format(creatureAI.getCumulativeAttack()/entNum) + "," 
				+ df2.format(creatureAI.getCumulativeDef()/entNum) + "," 
				+ df2.format(creatureAI.getCumulativeSpeed()/entNum) + "," 
				+ df2.format(creatureAI.getCumulativeInt()/entNum) + "," 
				+ df2.format(Spawn.getEntityList().size()) + "," 
				+ df2.format(creatureAI.getAdultMaleNumber()) + "," 
				+ df2.format(creatureAI.getAdultFemaleNumber()) + ","
				+ df2.format(creatureAI.getChildMaleNumber()) + ","
				+ df2.format(creatureAI.getChildFemaleNumber())
				);
		
		//every 30 days for 10 years
		if(Clock.ticksSinceGameStart() % 30 == 0) {
			monthpw.println(Clock.ticksSinceGameStart() + "," 
					+ df2.format(creatureAI.getCumulativeAttack()/entNum) + "," 
					+ df2.format(creatureAI.getCumulativeDef()/entNum) + "," 
					+ df2.format(creatureAI.getCumulativeSpeed()/entNum) + "," 
					+ df2.format(creatureAI.getCumulativeInt()/entNum) + "," 
					+ df2.format(Spawn.getEntityList().size()) + "," 
					+ df2.format(creatureAI.getAdultMaleNumber()) + "," 
					+ df2.format(creatureAI.getAdultFemaleNumber()) + ","
					+ df2.format(creatureAI.getChildMaleNumber()) + ","
					+ df2.format(creatureAI.getChildFemaleNumber())
					);
		}
		
		//once a year until you close the file
		if(Clock.ticksSinceGameStart() % 360 == 0) {
			yearpw.println(Clock.ticksSinceGameStart() + "," 
					+ df2.format(creatureAI.getCumulativeAttack()/entNum) + "," 
					+ df2.format(creatureAI.getCumulativeDef()/entNum) + "," 
					+ df2.format(creatureAI.getCumulativeSpeed()/entNum) + "," 
					+ df2.format(creatureAI.getCumulativeInt()/entNum) + "," 
					+ df2.format(Spawn.getEntityList().size()) + "," 
					+ df2.format(creatureAI.getAdultMaleNumber()) + "," 
					+ df2.format(creatureAI.getAdultFemaleNumber()) + ","
					+ df2.format(creatureAI.getChildMaleNumber()) + ","
					+ df2.format(creatureAI.getChildFemaleNumber())
					);
			//every year, make a new day file
			daypw.close();
			dayWriter();
			//every 10 years, make a new month file
			if(Clock.ticksSinceGameStart() % 3600 == 0) {
				monthpw.close();
				monthWriter();
			}
		}

		clearUIStats();
	}
	
	public static void closeYearFileWriter() {
		yearpw.close();
	}
	
	private static void clearUIStats() {
		creatureAI.setCumulativeAttack(0);
		creatureAI.setCumulativeDef(0);
		creatureAI.setCumulativeSpeed(0);
		creatureAI.setCumulativeInt(0);
		creatureAI.setAdultMaleNumber(0);
		creatureAI.setAdultFemaleNumber(0);
		creatureAI.setChildMaleNumber(0);
		creatureAI.setChildFemaleNumber(0);
	}
	
	private static void dayWriter() throws IOException {
		File dayfile = new File("results\\" +getFileName() + "-day.csv");
	  	if(!dayfile.exists()){
		  	dayfile.createNewFile();
		}
	  
		FileWriter dayfw = new FileWriter(dayfile,true);
	  	BufferedWriter daybw = new BufferedWriter(dayfw);
	  	daypw = new PrintWriter(daybw);
	}
	
	private static void monthWriter() throws IOException {
		File monthfile = new File("results\\" +getFileName() + "-month.csv");
	  	if(!monthfile.exists()){
		  	monthfile.createNewFile();
		}
	  
		FileWriter monthfw = new FileWriter(monthfile,true);
	  	BufferedWriter monthbw = new BufferedWriter(monthfw);
	  	monthpw = new PrintWriter(monthbw);
	}
	
	private static void yearWriter() throws IOException {
		File yearfile = new File("results\\" + getFileName() + "-year.csv");
	  	if(!yearfile.exists()){
	  		yearfile.createNewFile();
		}
	  
		FileWriter yearfw = new FileWriter(yearfile,true);
	  	BufferedWriter yearbw = new BufferedWriter(yearfw);
	  	yearpw = new PrintWriter(yearbw);
	}
	
	public static String getFileName() {
		long time = Clock.ticksSinceGameStart();
		String fileName = Long.toString(time);
		return fileName;
	}
}

