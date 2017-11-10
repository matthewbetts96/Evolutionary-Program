package data;

import static helpers.Startup.createSession;
import static helpers.Startup.firstTimeCleanUp;

import org.lwjgl.opengl.Display;

import helpers.Clock;
import helpers.Config;

public class Run {

	public Run() {
		createSession();
		
		Spawn spawnEntity = new Spawn();
		for(int i = 0; i < Config.getEntityNumber(); i++) {
			spawnEntity.SpawnEntities();
		}
		
		TileGrid grid = new TileGrid();
		firstTimeCleanUp(grid);

		while(!Display.isCloseRequested()) {
			Clock.update();
			if(!Clock.isPaused()) {
				//Draw/Redraw the grid 
				grid.Draw();
				//Update entities on the grid
				spawnEntity.Update(grid);
			}
			//Update the display 
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		
	}
		
	public static void main(String[] args) {
		new Run();
	}

}
