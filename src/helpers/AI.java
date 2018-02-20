package helpers;

import static helpers.Artist.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import data.Entity;
import data.Spawn;
import data.TileGrid;
import data.TileType;
import data.findBestTile;
import data.tilesInVision;

public class AI {
	public static void setNewDirection(Entity e, TileGrid grid, int x, int y) {
		
		findBestTile[] surroundingTiles = new findBestTile[15];
		
		surroundingTiles = tilesInVision.getFacingTiles(e, grid, x, y);

		//Sort tiles
		Arrays.sort(surroundingTiles);
		
		//facing value default is north 
		String largestValue = "north";
		try {
			largestValue = surroundingTiles[0].getDirection();
		}catch(Exception ex) {
			System.out.println("Error finding best tile.");
		}
		
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
		e.setIsFacing(surroundingTiles[0].getDirection());
		double origSpeed = e.getOrigSpeed();
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
	
	public static ArrayList<Integer> crossBreed(Entity male, Entity female) {
		ArrayList<Integer> childStats = new ArrayList<Integer>();
		childStats.clear();
		
		//Mix male and female stats and return the stats of the offspring
		
		int childSpeed = getGaussian(male.getOrigSpeed(), female.getOrigSpeed());
		int childAttack = getGaussian(male.getAttackVal(), female.getAttackVal());
		int childDefense = getGaussian(male.getDefenseVal(), female.getDefenseVal());
		int childIntelligence = getGaussian(male.getIntelligence(), female.getIntelligence());
		
		childStats.add((int)male.getX() + 1); //the coords for both will be the same
		childStats.add((int)male.getY() + 1);
		childStats.add(childSpeed); 		
		childStats.add(childAttack);		
		childStats.add(childDefense);		
		childStats.add(childIntelligence);	
		return childStats;
	}
	
	public static int getGaussian(int male, int female) {
		int high = 0;
		int low = 0;
		Random x = new Random();
		
		if(male > female) {
			high = male;
			low = female;
		} else {
			high = female;
			low = male;
		}
		return (int)x.nextGaussian() * (high - (low*2)) + (high - ((high - low)/2));
	}
	
	public static void updateTiles(TileGrid grid) {
		for(int i = 0; i < Config.getWidth()/Config.getSize(); i++) {
			for(int j = 0; j < Config.getHeight()/Config.getSize(); j++) {
				
				//while we are looping through the list of tiles, we might as well clear them
				//and/or spawn any offspring or do any fighting
				
				int numOfCreaturesOnTile = grid.GetTile(i, j).getCreaturesOnTile().size();
				ArrayList<Entity> creaturesOnTile = grid.GetTile(i, j).getCreaturesOnTile();
				if(numOfCreaturesOnTile >= 2) {
					for(int k = 0; i < creaturesOnTile.size(); k++) {
						
					}
					ArrayList<Entity> creatureList = Spawn.getEntityList();
					
					//TODO
					
					
					
					//Only get the first 2 on the tile, ignore all the others
					Entity e1 = grid.GetTile(i, j).getCreaturesOnTile().get(0);
					Entity e2 = grid.GetTile(i, j).getCreaturesOnTile().get(1);
					
					if(e1.getTicksSinceLastChild() > 200 && e1.getTicksSinceLastChild() > 200) {
						ArrayList<Integer> crossBreedOutcome = crossBreed(e1, e2);
						creatureList.add(new Entity(crossBreedOutcome));
						Spawn.setEntityList(creatureList);
						e1.setTicksSinceLastChild(0);
						e2.setTicksSinceLastChild(0);
					}
				}
				
				int food = grid.GetTile(i, j).getTotalFood();
				TileType typeOfTile = grid.GetTile(i, j).getType();
				grid.GetTile(i, j).clearEntityList();
				switch(typeOfTile) {
					case Mountains:
						if(food < typeOfTile.getMaxfood()) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + typeOfTile.getFoodRegen());
						}
						break;
					case Water:
						if(food < typeOfTile.getMaxfood()) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + typeOfTile.getFoodRegen());
						}
						break;
					case Highlands:
						if(food < typeOfTile.getMaxfood()) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + typeOfTile.getFoodRegen());
						}
						break;
					case Grass:
						if(food < typeOfTile.getMaxfood()) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + typeOfTile.getFoodRegen());
						}
						if(food <= 70) {
							grid.GetTile(i, j).setType(TileType.Dirt);
							grid.GetTile(i, j).setTexture(QuickLoad("dirt"));
						}
						break;
					case Dirt:
						if(food < typeOfTile.getMaxfood()) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + typeOfTile.getFoodRegen());
						}
						
						if(food > 91) {
							
							grid.GetTile(i, j).setType(TileType.Grass);
							grid.GetTile(i, j).setTexture(QuickLoad("grass"));
						}
						break;
					case Sand:
						break;
					case Border:
						break;
				default:
					System.out.println("Error in replenish food, unknown tiletype = "+ typeOfTile);
					break;
				}
			}
		}
	}
	
	public static int eatFood(int x, int y, TileGrid grid) {
		int food = 0;
		try {
			food = grid.GetTile(x, y).getTotalFood();
		} catch(Exception ex) {
			return -10;
		}
		if(food >= 10) {
			food = food - 10;
			grid.GetTile(x, y).setTotalFood(food);
			return 10;
		} else {
			grid.GetTile(x, y).setTotalFood(0);
			return food -10;
		}
	}
	
	public static void reverse(Entity e) {
		e.setxSpeed(e.getxSpeed() * -1);
		e.setySpeed(e.getySpeed() * -1);	
		
		//reverse the facing direction 
		switch(e.getIsFacing()) {
			case "north":
				e.setIsFacing("south");
				break;
				
			case "south":
				e.setIsFacing("north");
				break;
				
			case "east":
				e.setIsFacing("west");
				break;
				
			case "west":
				e.setIsFacing("east");
				break;
				
			case "northwest":
				e.setIsFacing("southeast");
				break;
				
			case "northeast":
				e.setIsFacing("southwest");
				break;
				
			case "southwest":
				e.setIsFacing("northwest");
				break;
				
			case "southeast":
				e.setIsFacing("northwest");
				break;
		}
	}
}
