package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.opengl.Display;

import helpers.Clock;
import helpers.Config;



public class GameCycle {
	
	private UI gameUI;
	
	public void start() throws IOException {
		//Create a new grid of tiles
		TileGrid grid = new TileGrid();
		
		gameUI = new UI();
		
		//Set up stats collection
		PrintWriter	pw = setupOutput();
		
		Spawn spawnEntity = new Spawn();
		
		//Spawn a new entity up until the number defined in the config
		for(int i = 0; i < Config.getEntityNumber(); i++) {
			spawnEntity.SpawnEntities();
		}
		
		//Clear entites on impassable terrain
		firstTimeCleanUp(grid);
		
		//While the X has not been pressed
		while(!Display.isCloseRequested()) {
			
			//Update the clock
			Clock.update();
			
			//Draw the grid and do any interactions of the entities on it
			TileGrid.Draw(grid);
			
			//Do the AI action for each creature
			creatureAI.creatureActions(grid);
			
			//Update stats
			UI.updateStats(pw, grid, gameUI);

			//Update and sync the display
			Display.update();
			Display.sync(60);
		}
		//Close the display & printwriter when the loop finishes
		pw.close();
		Display.destroy();
	}
	
	private PrintWriter setupOutput() throws IOException {
		File file = new File(Config.getOutputFileName());
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file,true);
  	  	BufferedWriter bw = new BufferedWriter(fw);
  	  	PrintWriter pw = new PrintWriter(bw);
		return pw;
	} 

	private static void firstTimeCleanUp(TileGrid grid){
		ArrayList<Entity> entityList = Spawn.getEntityList();
		int i = 0;
		for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
			Entity e = iterator.next();
			int xPos = (int)e.getxPosition()/Config.getSize();
			int yPos = (int)e.getyPosition()/Config.getSize();
			if((grid.GetTile(xPos, yPos).getType() == TileType.Mountains || grid.GetTile(xPos, yPos).getType() == TileType.Water)) {
				i++;
				iterator.remove();
			}
		}
		System.out.println(i + " entities removed for being on impassible terrain.");
		Spawn.setEntityList(entityList);
	}
}
