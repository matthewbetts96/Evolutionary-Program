package data;

import java.io.IOException;
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
			UI.updateStats(grid, gameUI);

			//Update and sync the display
			Display.update();
			Display.sync(60);
		}
		//Close the display & printwriter when the loop finishes
		UI.closeYearFileWriter();
		Display.destroy();
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
