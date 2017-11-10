package data;

import static helpers.AI.eatFood;
import static helpers.AI.reverse;
import static helpers.AI.setNewDirection;
import static helpers.AI.updateTiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import helpers.Clock;
import helpers.Config;

public class Spawn {
	
	private static ArrayList<Entity> entityList = new ArrayList<Entity>();
	
	public Spawn() {
		
	}
	
	public void SpawnEntities() {
		Random i = new Random();
		Random j = new Random();
		entityList.add(new Entity(EntityType.Prey, i, j));	
	}
	
	public static ArrayList<Entity> getEntityList() {
		return entityList;
	}

	public static void setEntityList(ArrayList<Entity> entityList) {
		Spawn.entityList = entityList;
	}
	
	public void Update(TileGrid grid) {
		if(!Clock.isPaused() && Clock.ticksSinceGameStart() > 100) {
			for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
				Entity e = iterator.next();
				
				int xPos = (int)e.getX()/Config.getSize();
				int yPos = (int)e.getY()/Config.getSize();
				
				int entityCurrentHunger = e.getCurrentHunger();
				int entityMaxHunger = e.getMaxHunger();
				int availableFood = 0;
				
				if(entityCurrentHunger < entityMaxHunger*0.9) {
					availableFood = eatFood(xPos,yPos,grid);
					entityCurrentHunger = entityCurrentHunger + availableFood;
					e.setCurrentHunger(entityCurrentHunger);
				} 
				
				if(entityCurrentHunger < 0) {
					System.out.println(e + " has died.");
					System.out.println("______________________");
					iterator.remove();
				}
				
				if(grid.GetTile(xPos, yPos).getType().getTextureName() == "border") {
					reverse(e);
				} else if(Clock.ticksSinceGameStart() % e.getIntelligence() == 0) {
					setNewDirection(e,grid, xPos, yPos);
					e.setCurrentHunger(e.getCurrentHunger() - 1);
				}
							
				e.Update();
				e.Draw();
				try {
					grid.GetTile(xPos, yPos).addEntity(e);
				} catch(Exception ex) { }
			}
		}
		//replenishes tiles as well as clears the 
		updateTiles(grid);
	}
	
}
