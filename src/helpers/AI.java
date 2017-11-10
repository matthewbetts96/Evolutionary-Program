package helpers;

import static helpers.Artist.QuickLoad;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import data.Entity;
import data.Spawn;
import data.TileGrid;
import data.TileType;
import data.findBestTile;

public class AI {
	public static void setNewDirection(Entity e, TileGrid grid, int x, int y) {
		
		int food = 0;
		findBestTile[] surroundingTiles = new findBestTile[9];
		
		//We're setting this to -100 for now as it makes the game look like it's lagging
		try {
			food = grid.GetTile(x, y).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z0 = new findBestTile(x, y, -100, "z0");
		surroundingTiles[0] = z0;
		
		try {
			food = grid.GetTile(x + 1, y).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z1 = new findBestTile(x + 1, y, food, "z1");
		surroundingTiles[1] = z1;
		
		try {
			food = grid.GetTile(x, y + 1).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z2 = new findBestTile(x, y + 1, food, "z2");
		surroundingTiles[2] = z2;
		
		try {
			food = grid.GetTile(x + 1, y + 1).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z3 = new findBestTile(x + 1, y + 1, food, "z3");
		surroundingTiles[3] = z3;
		
		try {
			food = grid.GetTile(x - 1, y - 1).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z4 = new findBestTile(x - 1, y - 1, food, "z4");
		surroundingTiles[4] = z4;
		
		try {
			food = grid.GetTile(x, y - 1).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z5 = new findBestTile(x, y - 1, food, "z5");
		surroundingTiles[5] = z5;
		
		try {
			food = grid.GetTile(x - 1, y).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z6 = new findBestTile(x - 1, y, food, "z6");
		surroundingTiles[6] = z6;
		
		try {
			food = grid.GetTile(x + 1, y - 1).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z7 = new findBestTile(x + 1, y - 1, food, "z7");
		surroundingTiles[7] = z7;
		
		try {
			food = grid.GetTile(x - 1, y + 1).getTotalFood();
		} catch(Exception ex) {
			food = 0;
		}
		findBestTile z8 = new findBestTile(x - 1, y + 1, food, "z8");
		surroundingTiles[8] = z8;
		
		//Sort tiles
		Arrays.sort(surroundingTiles);
		
		Random i = new Random();
		int rand = i.nextInt(5);
		String largestValue = "z1";
		try {
			largestValue = surroundingTiles[rand].getDirection();
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
	}
	
	public static ArrayList<Integer> crossBreed(Entity male, Entity female) {
		ArrayList<Integer> childStats = new ArrayList<Integer>();
		//Mix male and female stats and return the stats of the offspring
		
		
		/* EXPERIMENTAL 
		
		//byte[] byteChildSpeed = XORbyteArrays(toByteArray(male.getOrigSpeed()), toByteArray(female.getOrigSpeed()));
		
		System.out.println(male.getIntelligence());
		System.out.println(female.getIntelligence());
		
		byte[] ar1 = toByteArray(male.getIntelligence());
		byte[] ar2 = toByteArray(female.getIntelligence());
		
		for(int i =0; i < ar1.length; i++) {
			System.out.print(ar1[i]);
		}
		System.out.println();
		for(int i =0; i < ar1.length; i++) {
			System.out.print(ar2[i]);
		}
		
		System.out.println();
		//System.out.println(female.getOrigSpeed());
		//System.out.println(toInt(byteChildSpeed));
				 
		//System.out.println((int)male.getOrigSpeed());
		//System.out.println((int)female.getOrigSpeed());
		//System.out.println(childSpeed);
		
		byte[] byteChildSpeed = XORbyteArrays(toByteArray(male.getIntelligence()), toByteArray(female.getIntelligence()));
		System.out.println(byteChildSpeed);
		System.out.println(toInt(byteChildSpeed));
		System.out.println("_____________________");
		
		
		*/
		
		//childStats.add();
		
		
		return childStats;
	}
	
	public static byte[] toByteArray(int value) {
	    byte[] bytes = new byte[8];
	    ByteBuffer.wrap(bytes).putInt(value);
	    return bytes;
	}
	
	public static byte[] toByteArray(double value) {
	    byte[] bytes = new byte[8];
	    ByteBuffer.wrap(bytes).putDouble(value);
	    return bytes;
	}
	
	public static double toDouble(byte[] bytes) {
	    return ByteBuffer.wrap(bytes).getDouble();
	}
	
	public static int toInt(byte[] bytes) {
	    return ByteBuffer.wrap(bytes).getInt();
	}
	
	public static byte[] XORbyteArrays(byte[] array_1, byte[] array_2) {
		byte[] array_3 = new byte[8];

		int i = 0;
		for (byte b : array_1)
		    array_3[i] = (byte) (b ^ array_2[i++]);
		
		return array_3;
	}
	
	public static void updateTiles(TileGrid grid) {
		for(int i = 0; i < Config.getWidth()/Config.getSize(); i++) {
			for(int j = 0; j < Config.getHeight()/Config.getSize(); j++) {
				int food = grid.GetTile(i, j).getTotalFood();
				TileType typeOfTile = grid.GetTile(i, j).getType();
				
				//while we are looping through the list of tiles, we might as well clear them
				//and/or spawn any offspring
				if(grid.GetTile(i, j).getCreaturesOnTile().size() >= 2) {
					ArrayList<Entity> creatureList = Spawn.getEntityList();
					
					//Only get the first 2 on the tile, ignore all the others
					Entity e1 = grid.GetTile(i, j).getCreaturesOnTile().get(0);
					Entity e2 = grid.GetTile(i, j).getCreaturesOnTile().get(1);
					
					ArrayList<Integer> crossBreedOutcome = crossBreed(e1, e2);
					//creatureList.add(new Entity(crossBreedOutcome));
					Spawn.setEntityList(creatureList);
				}
				
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
			food = food -10;
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
	}
}
