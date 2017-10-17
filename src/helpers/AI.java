package helpers;

import static helpers.Artist.QuickLoad;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

import data.Entity;
import data.TileGrid;
import data.TileType;
import data.findBestTile;

public class AI {
	
	private static ArrayList<findBestTile> tilesEatenFrom = new ArrayList<findBestTile>();
	
	//Stops entities moving on tiles that they shouldn't
	public static boolean noImpassableTiles(Entity e, int x, int y, double xSpeed, double ySpeed, TileGrid grid) {
		try {
			if(!grid.GetTile(x,y).getType().isTraversable()) {
				e.setxSpeed(xSpeed * -1);
				e.setySpeed(ySpeed * -1);
				return true;
			} 
		} catch(Exception ex) {
			ex.printStackTrace();
			return true;
			
		}
		return false;
	}
	
	/* setNewDirection uses findBestTile and Comparable<> to find 
	 * and return the best tile within the entities radius
	 * https://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
	 */
	
	public static boolean setNewDirection(Entity e, TileGrid grid, int x, int y) {
	
		int food = 0;
		findBestTile[] surroundingTiles = new findBestTile[9];
		
		//We're setting this to -100 for now as it makes the game look like it's lagging
		food = grid.GetTile(x, y).getTotalFood();
		findBestTile z0 = new findBestTile(x, y, -100, "z0", grid.GetTile(x, y).getType());
		surroundingTiles[0] = z0;
		
		food = grid.GetTile(x + 1, y).getTotalFood();
		findBestTile z1 = new findBestTile(x + 1, y, food, "z1", grid.GetTile(x + 1, y).getType());
		surroundingTiles[1] = z1;
		
		food = grid.GetTile(x, y + 1).getTotalFood();
		findBestTile z2 = new findBestTile(x, y + 1, food, "z2", grid.GetTile(x, y + 1).getType());
		surroundingTiles[2] = z2;
		
		food = grid.GetTile(x + 1, y + 1).getTotalFood();
		findBestTile z3 = new findBestTile(x + 1, y + 1, food, "z3", grid.GetTile(x + 1, y + 1).getType());
		surroundingTiles[3] = z3;
		
		food = grid.GetTile(x - 1, y - 1).getTotalFood();
		findBestTile z4 = new findBestTile(x - 1, y - 1, food, "z4", grid.GetTile(x - 1, y - 1).getType());
		surroundingTiles[4] = z4;
		
		food = grid.GetTile(x, y - 1).getTotalFood();
		findBestTile z5 = new findBestTile(x, y - 1, food, "z5", grid.GetTile(x, y - 1).getType());
		surroundingTiles[5] = z5;
		
		food = grid.GetTile(x - 1, y).getTotalFood();;
		findBestTile z6 = new findBestTile(x - 1, y, food, "z6", grid.GetTile(x - 1, y).getType());
		surroundingTiles[6] = z6;
		
		food = grid.GetTile(x + 1, y - 1).getTotalFood();
		findBestTile z7 = new findBestTile(x + 1, y - 1, food, "z7", grid.GetTile(x + 1, y - 1).getType());
		surroundingTiles[7] = z7;
		
		food = grid.GetTile(x - 1, y + 1).getTotalFood();
		findBestTile z8 = new findBestTile(x - 1, y + 1, food, "z8", grid.GetTile(x - 1, y + 1).getType());
		surroundingTiles[8] = z8;
		
		//Sort tiles
		Arrays.sort(surroundingTiles);
			
		String largestValue = "z1";
		try {
			largestValue = surroundingTiles[0].getDirection();
			tilesEatenFrom.add(surroundingTiles[0]);
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
		
		double orgSpeed = e.getOrigSpeed();
		switch(largestValue) {
			case "z0":
				e.setxSpeed(0);
				e.setySpeed(0);
				break;
			case "z1":
				e.setxSpeed(orgSpeed);
				e.setySpeed(0);
				break;
			case "z2":
				e.setxSpeed(0);
				e.setySpeed(orgSpeed);
				break;
			case "z3":
				e.setxSpeed(orgSpeed);
				e.setySpeed(orgSpeed);
				break;
			case "z4":
				e.setxSpeed(-orgSpeed);
				e.setySpeed(-orgSpeed);
				break;	
			case "z5":
				e.setxSpeed(-orgSpeed);
				e.setySpeed(0);
				break;
			case "z6":
				e.setxSpeed(-orgSpeed);
				e.setySpeed(0);
				break;
			case "z7":
				e.setxSpeed(orgSpeed);
				e.setySpeed(-orgSpeed);
				break;
			case "z8":
				e.setxSpeed(-orgSpeed);
				e.setySpeed(orgSpeed);
				break;
		}
		return false;
	}
	
	public static int checkFood(int x, int y, TileGrid grid) {
		int food = 0;
		try {
			food = grid.GetTile(x, y).getTotalFood();
		} catch(Exception ex) {
			return 0;
		}
		if(food >= 5) {
			grid.GetTile(x, y).setTotalFood(food - 5);
			return 5;
		} else {
			grid.GetTile(x, y).setTotalFood(0);
			return food - 5;
		}
	}
	
	//refreshes the food on a tile up to a maximum
	public static void replenishFood(TileGrid grid) {
		
		for(findBestTile tile: tilesEatenFrom) {
			int x = tile.getxCoord();
			int y = tile.getyCoord();
			int food = tile.getCurrentFood();
			TileType t = tile.getTileType();
			
			switch(t) {											
				case Dirt: 
					grid.GetTile(x, y).setTotalFood(grid.GetTile(x, y).getTotalFood() + grid.GetTile(x, y).getFoodRegen());
					if(food >= 80) {
						grid.GetTile(x, y).setTexture(QuickLoad("grass"));
						grid.GetTile(x, y).setType(TileType.Grass);
					}
					break;	
									
				case Grass: 
					if(food <= 100) {
						grid.GetTile(x, y).setTotalFood(grid.GetTile(x, y).getTotalFood() + grid.GetTile(x, y).getFoodRegen());
					} 
					if(food <= 80) {
						grid.GetTile(x, y).setTexture(QuickLoad("dirt"));
						grid.GetTile(x, y).setType(TileType.Dirt);
					}
					break;
									
				case Highlands:
					if(food <= 40) {
						grid.GetTile(x, y).setTotalFood(grid.GetTile(x, y).getTotalFood() + grid.GetTile(x, y).getFoodRegen());
					} 
					break;
				case Sand:
					if(food <= 25) {
						grid.GetTile(x, y).setTotalFood(grid.GetTile(x, y).getTotalFood() + grid.GetTile(x, y).getFoodRegen());
					} 
					break;
				
				default: 
					System.out.println("aSDASFDAFasdf");
					break;
			}
		}
		tilesEatenFrom.clear();
	}
}
 