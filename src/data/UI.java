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
		
		int year = (int) (Clock.ticksSinceGameStart()/360);
		int month = (int) (Clock.ticksSinceGameStart() - (year *360))/30;
		int day = (int) (Clock.ticksSinceGameStart() - (year *360) - (month * 30));
		
		//so dates won't start at 0
		year++;
		month++;
		day++;
		
		gameUI.drawText(850, 20, "Tick(s) = " + Clock.ticksSinceGameStart());
		gameUI.drawText(850, 40, "Date: " + day + "/"+ month + "/"+ year);
		
		gameUI.drawText(850, 220, "X :" + mouseX);
		gameUI.drawText(850, 240, "Y :" + mouseY);
		gameUI.drawText(850, 260, "Tile Type :" + tile.getType().getTextureName());
		gameUI.drawText(850, 280, "Food :" + tile.getCurrentFood());
		gameUI.drawText(850, 300, "Food Regen :" + tile.getFoodRegen());
		
		gameUI.drawText(850, 340, "Avg Attack = " + df2.format(creatureAI.getCumulativeAttack()/entNum));
		gameUI.drawText(850, 360, "Avg Def = " + df2.format(creatureAI.getCumulativeDef()/entNum));
		gameUI.drawText(850, 380, "Avg Speed = " + df2.format(creatureAI.getCumulativeSpeed()/entNum));
		gameUI.drawText(850, 400, "Avg Int = " + df2.format(creatureAI.getCumulativeInt()/entNum));
		
		gameUI.drawText(850, 440, "Adult Male = " + df2.format(creatureAI.getAdultMaleNumber()));
		gameUI.drawText(850, 460, "Adult Female = " + df2.format(creatureAI.getAdultFemaleNumber()));
		gameUI.drawText(850, 480, "Child Male = " + df2.format(creatureAI.getChildMaleNumber()));
		gameUI.drawText(850, 500, "Child Female = " + df2.format(creatureAI.getChildFemaleNumber()));
		
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
			if(Clock.ticksSinceGameStart() % 36000 == 0) {
				yearpw.close();
				yearWriter();
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
		File dayfile = new File("results//Year " + getFileName() + "-" + (Integer.parseInt(getFileName())+1) + " - daily logging.csv");
	  	if(!dayfile.exists()){
		  	dayfile.createNewFile();
		}
	  
		FileWriter dayfw = new FileWriter(dayfile,true);
	  	BufferedWriter daybw = new BufferedWriter(dayfw);
	  	daypw = new PrintWriter(daybw);
	}
	
	private static void monthWriter() throws IOException {
		File monthfile = new File("results//Year " + getFileName() + "-" + (Integer.parseInt(getFileName())+9) +" - monthly logging.csv");
	  	if(!monthfile.exists()){
		  	monthfile.createNewFile();
		}
	  
		FileWriter monthfw = new FileWriter(monthfile,true);
	  	BufferedWriter monthbw = new BufferedWriter(monthfw);
	  	monthpw = new PrintWriter(monthbw);
	}
	
	private static void yearWriter() throws IOException {
		File yearfile = new File("results//Year " + getFileName() + "-" + (Integer.parseInt(getFileName())+99) + " - yearly logging.csv");
	  	if(!yearfile.exists()){
	  		yearfile.createNewFile();
		}
	  
		FileWriter yearfw = new FileWriter(yearfile,true);
	  	BufferedWriter yearbw = new BufferedWriter(yearfw);
	  	yearpw = new PrintWriter(yearbw);
	}
	
	public static String getFileName() {
		
		int year = (int) (Clock.ticksSinceGameStart()/360);
		year++;
		String fileName = String.format("%d",year);
		return fileName;
	}
}

