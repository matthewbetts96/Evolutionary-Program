package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static helpers.AI.*;
import helpers.config;
import helpers.Clock;

public class Spawn {
	private static ArrayList<Entity> entityList = new ArrayList<Entity>();
	private int availableFood = 0;
	private boolean isOutofBounds = false;
	private boolean alreadyChangedDirection = false;
	public Spawn() {
	
	}
	
	public void Update(TileGrid grid) {
		if(!Clock.isPaused() && Clock.ticksSinceGameStart() > 100) {
			for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
			    Entity e = iterator.next();
			    int xPos = (int)e.getX()/config.getTilesize();
				int yPos = (int)e.getY()/config.getTilesize();
				double xSpeed = e.getxSpeed();
				double ySpeed = e.getySpeed();
				
				int entityCurrentHunger = e.getCurrentHunger();
				int entityMaxHunger = e.getMaxHunger();
				
				/*	if the entity is at 90% of it's hunger meter, then eat, else don't.
				 *  availableFood is a boolean to determine whether the entity starved if there was no food on the tile 
				 *  we do another check to see if the entities hunger is < 0, if it is then it dies, else nothing
				 *  happens and it tries again to eat next tick
				 */
				
				if(entityCurrentHunger < entityMaxHunger*0.9) {
					availableFood = checkFood(xPos,yPos,grid);
					entityCurrentHunger = entityCurrentHunger + availableFood;
					e.setCurrentHunger(entityCurrentHunger);
				} 
		
				if(entityCurrentHunger < 0) {
					iterator.remove();
				} else {
					alreadyChangedDirection = noImpassableTiles(e, xPos, yPos, xSpeed, ySpeed, grid);
			    	
			    	if(!alreadyChangedDirection) {
			    		//System.out.println("changeDirection");
			    		isOutofBounds = setNewDirection(e,grid, xPos, yPos);
			    		if(isOutofBounds) {
					    	iterator.remove();
					    }
			    	}
			    }
				e.setCurrentHunger(entityCurrentHunger - 1);
			    e.Update();
				e.Draw();
				isOutofBounds = false;
			}
			replenishFood(grid);
		}
	}
	
	//Add a standard entity with the standard texture, all other values are random
	public void SpawnEntities() {
		Random i = new Random();
		Random j = new Random();
		entityList.add(new Entity(i, j));	
	}
	
	
	public static ArrayList<Entity> getEntityList() {
		return entityList;
	}

	public static void setEntityList(ArrayList<Entity> entityList) {
		Spawn.entityList = entityList;
	}
}













