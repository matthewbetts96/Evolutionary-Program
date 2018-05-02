package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import helpers.Clock;
import helpers.Config;

import data.tilesInVision;
import data.findBestTile;

public class creatureAI {
	private static ArrayList<Entity> currentEntityList = new ArrayList<Entity>();
	//Static variables for stat collection
	private static float cumulativeAttack = 0;
	private static float cumulativeDef = 0;
	private static float cumulativeSpeed = 0;
	private static float cumulativeInt = 0;
	private static int cumulativeAge = 0;
	private static int adultMaleNumber = 0;
	private static int adultFemaleNumber = 0;
	private static int childMaleNumber = 0;
	private static int childFemaleNumber = 0;
	
	public static void creatureActions(TileGrid grid) {
		//Get the most recent version of the entity list
		currentEntityList = Spawn.getEntityList();
		
		//Now we iterate through each creature
		for (Iterator<Entity> iterator = currentEntityList.iterator(); iterator.hasNext();) {
			Entity e = iterator.next();
			
			//Get the 'tile' x/y position of the entity as we don't want it's 'true' x/y coords
			
			int xPos = (int)e.getxPosition()/Config.getSize();
			int yPos = (int)e.getyPosition()/Config.getSize();
			
			
			//Regardless of the entities int, do a movement calc on the first tick
			if(e.isFirst()) {
				movement(grid, e, xPos, yPos);
			}
			
			//Moving
			try {
				movement(grid, e, xPos, yPos);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			//Loss of food through living
			e.setCurrentHunger(e.getCurrentHunger() - 1);

			//Eating and food requirements
			eat(grid, e, xPos, yPos);
			
			//Aging
			birthday(e);
			
			//Moving and drawing the entity
			e.Update();
			e.Draw();
			
			//adding entity to a list on the tile it is on
			grid.GetTile(xPos, yPos).addEntity(e);
			
			//Removing the creature if they are dead
			if(e.isDead()) {
				iterator.remove();
			} else {
				//if not dead, collect stats
				statCollection(e);
			}
		}
		Spawn.setEntityList(currentEntityList);
	}
	
	private static void movement(TileGrid grid, Entity e, int xPos, int yPos) {
		if(grid.GetTile(xPos, yPos).isTraversable() != false) {
			if(Clock.ticksSinceGameStart() % e.getIntelligence() == 0) {
				setNewDirection(grid, e, xPos, yPos);
			}
		} else {
			reverse(e);
		}
		
		//Energy used in moving
		if(e.getBaseSpeed() < 0) {
			e.setCurrentHunger(e.getCurrentHunger() - (int)((-1*e.getBaseSpeed()/2)));
		} else {
			e.setCurrentHunger(e.getCurrentHunger() - (int)((1*e.getBaseSpeed()/2)));
		}
	}
	
	private static void setNewDirection(TileGrid grid, Entity e, int xPos, int yPos) {
		/*
		Positive y is down 
		Negative y is up 
		Positive x is right 
		Negative x is left
		
		Negative x,y is top left
		Positive x,y is bottom right
		
		Negative x, Positive y is bottom right 
		Negative y, Positive x is top right 
		
			x___________+x
		   y| 
			|
			|
		  	|
		  +y|
		*/
		
		findBestTile[] surroundingTiles = tilesInVision.getFacingTiles(e, grid, xPos, yPos);
		//Sort tiles
		Arrays.sort(surroundingTiles);
				
		//facing value default is north 
		String largestValue = "north";
		try {
			largestValue = surroundingTiles[0].getDirection();
		}catch(Exception ex) {
			System.out.println("Error finding best tile.");
		}
		
		e.setFacingDirection(surroundingTiles[0].getDirection());
		double origSpeed = e.getBaseSpeed();
		//TODO revert to pythagoras speed settings 
		switch(largestValue) {
			case "north":
				e.setxSpeed(0);
				e.setySpeed(-origSpeed);
				break;
			case "south":
				e.setxSpeed(0);
				e.setySpeed(origSpeed);
				break;
			case "east":
				e.setxSpeed(origSpeed);
				e.setySpeed(0);
				break;
			case "west":
				e.setxSpeed(-origSpeed);
				e.setySpeed(0);
				break;	
			case "northeast":
				e.setxSpeed(origSpeed);
				e.setySpeed(-origSpeed);
				break;
			case "northwest":
				e.setxSpeed(-origSpeed);
				e.setySpeed(-origSpeed);
				break;
			case "southeast":
				e.setxSpeed(origSpeed);
				e.setySpeed(-origSpeed);
				break;
			case "southwest":
				e.setxSpeed(-origSpeed);
				e.setySpeed(-origSpeed);
				break;
		}
		
	}
	
	//Reverses the entities direction
	private static void reverse(Entity e) {
		e.setxSpeed(e.getxSpeed() * -1);
		e.setySpeed(e.getySpeed() * -1);	
		
		//reverse the facing direction 
		switch(e.getFacingDirection()) {
			case "north":
				e.setFacingDirection("south");
				break;
				
			case "south":
				e.setFacingDirection("north");
				break;
				
			case "east":
				e.setFacingDirection("west");
				break;
				
			case "west":
				e.setFacingDirection("east");
				break;
				
			case "northwest":
				e.setFacingDirection("southeast");
				break;
				
			case "northeast":
				e.setFacingDirection("southwest");
				break;
				
			case "southwest":
				e.setFacingDirection("northwest");
				break;
				
			case "southeast":
				e.setFacingDirection("northwest");
				break;
		}
	}
	
	private static void birthday(Entity e) {
		e.setAge(e.getAge() + 1);
		
		e.setTicksSinceLastInteraction(e.getTicksSinceLastInteraction()+1);
		
		
		//TODO FIX THIS
		//Only females are limited to having children all the time
		if(e.isMale() == false) {
			e.setTicksSinceLastChild(e.getTicksSinceLastChild() + 1);
		} else {
			e.setTicksSinceLastChild(10000);
		}
		
		//entity lifespan = 50 years (18000 ticks)
		if(e.getAge() > 18000) {
			e.setDead(true);
		}
	}
	
	//Entities eating from tiles
	private static void eat(TileGrid grid, Entity e, int xPos, int yPos) {
		//if hunger above 90%, don't eat
		if(e.getCurrentHunger() < e.getMaxHunger()*0.9) {
			int foodOnTile = grid.GetTile(xPos, yPos).getCurrentFood();
			//if food is less than 10 on the tile
			if(foodOnTile <= 5) {
				e.setCurrentHunger(e.getCurrentHunger() + foodOnTile);
				grid.GetTile(xPos, yPos).setCurrentFood(0);
			} else {
				//remove 5 food from the tile, add 5 to the creature
				foodOnTile = foodOnTile - 5;
				grid.GetTile(xPos, yPos).setCurrentFood(foodOnTile);
				e.setCurrentHunger(e.getCurrentHunger() + 5);
			}
		} 
			
		if(e.getCurrentHunger() < 0) {
			e.setDead(true);
			System.out.println(e + " has died of starvation.");
		}
	}
	
	public static void statCollection(Entity e){
		cumulativeAttack += e.getAttackVal();
		cumulativeDef += e.getDefenseVal();
		cumulativeSpeed += e.getBaseSpeed();
		cumulativeInt += e.getIntelligence();
		cumulativeAge += e.getAge();
		
		if(e.getAge() > 5400 && e.isMale() == true) {
			adultMaleNumber++;
		} else if(e.getAge() > 5400 && e.isMale() == false) {
			adultFemaleNumber++;
		} else if(e.getAge() < 5400 && e.isMale() == true) {
			childMaleNumber++;
		} else if(e.getAge() < 5400 && e.isMale() == false){
			childFemaleNumber++;
		}	
	}
	
	
	//Stat collection getters and setters

	public static float getCumulativeAttack() {
		return cumulativeAttack;
	}

	public static void setCumulativeAttack(float cumulativeAttack) {
		creatureAI.cumulativeAttack = cumulativeAttack;
	}

	public static float getCumulativeDef() {
		return cumulativeDef;
	}

	public static void setCumulativeDef(float cumulativeDef) {
		creatureAI.cumulativeDef = cumulativeDef;
	}

	public static float getCumulativeSpeed() {
		return cumulativeSpeed;
	}

	public static void setCumulativeSpeed(float cumulativeSpeed) {
		creatureAI.cumulativeSpeed = cumulativeSpeed;
	}

	public static float getCumulativeInt() {
		return cumulativeInt;
	}

	public static void setCumulativeInt(float cumulativeInt) {
		creatureAI.cumulativeInt = cumulativeInt;
	}

	public static int getCumulativeAge() {
		return cumulativeAge;
	}

	public static void setCumulativeAge(int cumulativeAge) {
		creatureAI.cumulativeAge = cumulativeAge;
	}

	public static int getChildMaleNumber() {
		return childMaleNumber;
	}

	public static void setChildMaleNumber(int childMaleNumber) {
		creatureAI.childMaleNumber = childMaleNumber;
	}

	public static int getChildFemaleNumber() {
		return childFemaleNumber;
	}

	public static void setChildFemaleNumber(int childFemaleNumber) {
		creatureAI.childFemaleNumber = childFemaleNumber;
	}

	public static int getAdultMaleNumber() {
		return adultMaleNumber;
	}

	public static void setAdultMaleNumber(int adultMaleNumber) {
		creatureAI.adultMaleNumber = adultMaleNumber;
	}

	public static int getAdultFemaleNumber() {
		return adultFemaleNumber;
	}

	public static void setAdultFemaleNumber(int adultFemaleNumber) {
		creatureAI.adultFemaleNumber = adultFemaleNumber;
	}
	
	
}
