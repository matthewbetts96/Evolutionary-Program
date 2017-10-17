package data;

import static helpers.General.BeginSession;

import org.lwjgl.opengl.Display;

import helpers.Clock;
import helpers.config;
import helpers.General;

public class Run {
	
	public Run() {
		BeginSession();
		
		TileGrid grid = new TileGrid();
		
		//Spawns our entities
		Spawn spawnEntity = new Spawn();
		for(int i = 0; i < config.getEntitynumber(); i++) {
			spawnEntity.SpawnEntities();
		}
		
		General.firstTimeCleanUp(grid);
		
		//This will run until the window is closed with the X button
		while(!Display.isCloseRequested()) {
			//Update the clock (update delta time)
			Clock.update();
			
			//Draw/redraw the grid
			if(!Clock.isPaused()) {
				
				//Redraw the grid, this seems super inefficient need to find a way to make this faster as 
				//it is the current bottleneck with large maps and lots of entities
				//current idea is to only update the tiles that the entities have interacted with 
				grid.Draw();
				
				//Update entities on the grid
				spawnEntity.Update(grid);
				
				//Update the display
				Display.update();
				Display.sync(60); 
			}
		}
		Display.destroy();
	}
	
	public static void main(String[] args) {

		new Run();
	}
}