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
	private static boolean toRemove = false;
	private static int numberOfDeadEntities = 0;
	
	public Spawn() {}
	
	//Inital spawning method
	public void SpawnEntities() {
		Random i = new Random();
		Random j = new Random();
		
		//50/50 chance for each starting entity to be either prey or predator
		if(i.nextInt(2) == 1) {
			entityList.add(new Entity(EntityType.Prey, i, j));
		} else {
			entityList.add(new Entity(EntityType.Predator, i, j));
		}
	}
	
	/*
	 * Method that deals with each entity being updated
	 */
	public void Update(TileGrid grid) {
		if(!Clock.isPaused() && Clock.ticksSinceGameStart() > 100) {
			for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
				Entity e = iterator.next();
				
				int xPos = (int)e.getX()/Config.getSize();
				int yPos = (int)e.getY()/Config.getSize();
				
				int entityCurrentHunger = e.getCurrentHunger();
				int entityMaxHunger = e.getMaxHunger();
				String entityDirection = e.getIsFacing();
				int availableFood = 0;
				
				if(entityCurrentHunger < entityMaxHunger*0.6) {
					availableFood = eatFood(xPos,yPos,grid);
					entityCurrentHunger = entityCurrentHunger + availableFood;
					e.setCurrentHunger(entityCurrentHunger);
				} 
				
				if(entityCurrentHunger < 0) {
					//System.out.println(e + " has died of starvation.");
					//System.out.println("---------------------");
					toRemove = true;
				}
				
				//Passive food loss
				int passiveLoss = e.getCurrentHunger() - 2;
				e.setCurrentHunger(passiveLoss);
				
				//Entity movement
				try {
					if(grid.GetTile(xPos, yPos).isTraversable() == false) {
						reverse(e);
					} else if(Clock.ticksSinceGameStart() % e.getIntelligence() == 0) {
						setNewDirection(e,grid, xPos, yPos);
						
						//Energy used in moving
						int x = 0;
						if(e.getOrigSpeed() < 0) {
							x = e.getCurrentHunger() - (-4*e.getOrigSpeed());
						} else {
							x = e.getCurrentHunger() - (4*e.getOrigSpeed());
						}
						e.setCurrentHunger(x);
					}
				} catch(Exception ex) { }

				e.birthday(); //Happy birthday to you! Happy birthday to you!
				int currentAge = e.getAge();
				
				//Kill entity if it gets too old
				if(currentAge >= 1500) {
					//System.out.println(e + " has died of old age.");
					//System.out.println("---------------------");
					toRemove = true;
				}
				
				e.Update();
				e.Draw();
				
				//Add the entity to the list of entities on the tile that it is on
				try {
					grid.GetTile(xPos, yPos).addEntity(e);
				} catch(Exception ex) { }
				
				
				//Rather than having several iterator.removes we'll just remove it once here
				if(toRemove) {
					iterator.remove();
					setNumberOfDeadEntities(getNumberOfDeadEntities() + 1);
					toRemove = false;
				}
			}
		}
	}

	public static int getNumberOfDeadEntities() {
		return numberOfDeadEntities;
	}

	public static void setNumberOfDeadEntities(int numberOfDeadEntities) {
		Spawn.numberOfDeadEntities = numberOfDeadEntities;
	}
	
	public static ArrayList<Entity> getEntityList() {
		return entityList;
	}

	public static void setEntityList(ArrayList<Entity> entityList) {
		Spawn.entityList = entityList;
	}
	
}
