package data;


import static helpers.Artist.BeginSession;
import org.lwjgl.opengl.Display;

import helpers.Clock;
import helpers.config;

public class Boot {
	
	public Boot() {
		BeginSession();
		
		TileGrid grid = new TileGrid();
		
		//Spawns our entities
		Spawn spawnEntity = new Spawn();
		for(int i = 0; i < config.getEntitynumber(); i++) {
			spawnEntity.SpawnEntities();
		}
		while(!Display.isCloseRequested()) {

			Clock.update();
			grid.Draw();
			spawnEntity.Update();
			Display.update();
			Display.sync(60); //in fps
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Boot();
	}
}
