package data;

import static helpers.AI.updateTiles;
import static helpers.Startup.createSession;
import static helpers.Startup.firstTimeCleanUp;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import helpers.Clock;
import helpers.Config;

public class Run {
	private UI gameUI;

	public Run() {
		createSession();
		gameUI = new UI();
		TileGrid grid = new TileGrid();
		
		Spawn spawnEntity = new Spawn();
		for(int i = 0; i < Config.getEntityNumber(); i++) {
			spawnEntity.SpawnEntities();
		}
	
		firstTimeCleanUp(grid);
		
		while(!Display.isCloseRequested()) {
			Clock.update();
			if(!Clock.isPaused()) {
				//Draw/Redraw the grid 
				grid.Draw();
				//Update entities on the grid
				spawnEntity.Update(grid);
				updateTiles(grid);
				
				//Update stats on screen
				updateStatistics(grid);
			}
			//Update the display 
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public void updateStatistics(TileGrid grid) {
		gameUI.drawText(820, 10, "Tick(s) = " + Clock.ticksSinceGameStart());
		gameUI.drawText(820, 30, "Entites alive = " + Spawn.getEntityList().size());
		gameUI.drawText(820, 50, "Entites died = " + Spawn.getNumberOfDeadEntities());
		
		int mouseX = Mouse.getX(); 
		int mouseY = 999 - Mouse.getY();
		
		//Get the tile you are hovering over
		Tile tile = grid.GetTile(((int)mouseX/Config.getSize()), ((int)mouseY/Config.getSize()));
		
		gameUI.drawText(820, 70, "Tile Type :" + tile.getType().getTextureName());
		gameUI.drawText(820, 90, "Food :" + tile.getTotalFood());
		gameUI.drawText(820, 110, "Food Regen :" + tile.getFoodRegen());

		
	}
	
	public static void main(String[] args) {
		new Run();
	}

}
