package helpers;

import java.util.ArrayList;
import java.util.Random;

import static helpers.Artist.*;

import data.Entity;
import data.TileGrid;
import data.TileType;

public class AI {
	
	public static Random random = new Random();
	
	public static boolean doAIStuff(Entity e, TileGrid grid, int x, int y) {
		double XSpeed = e.getxSpeed();
		double YSpeed = e.getySpeed();
		noImpassableTiles(e, x, y, XSpeed, YSpeed, grid);
		boolean enoughFood = eatFood(x,y,grid);
		return enoughFood;
	}
	
	//Stops entities moving on tiles that they shouldn't
	public static void noImpassableTiles(Entity e, int x, int y, double xSpeed, double ySpeed, TileGrid grid) {
		if(!grid.GetTile(x,y).getType().isTraversable()) {
			e.setxSpeed(xSpeed * -1);
			e.setySpeed(ySpeed * -1);
		} 
	}
	
	public static void setNewDirection(Entity e, TileGrid grid, int x, int y) {
		/*
		  e = the entity 
		  
		  		z1 z2 z3
		  		z4 e  z5
		 		z6 z7 z8  

				   x   |  y
				------------
		Up				-  0   | b  			
		Down			-  0   |-b
		Left 			- -a   | 0  
		Right 			-  a   | 0
		top-right 		-  Math.sqrt((a*a)+(b*b)   |-Math.sqrt((a*a)+(b*b)
		bottom-right 	-  Math.sqrt((a*a)+(b*b)   | Math.sqrt((a*a)+(b*b)
		top-left 		- -Math.sqrt((a*a)+(b*b)   |-Math.sqrt((a*a)+(b*b)
		bottom-left 	- -Math.sqrt((a*a)+(b*b)   | Math.sqrt((a*a)+(b*b)
		*/	
		double z1 = 0;
		double z2 = 0;
		double z3 = 0;
		double z4 = 0;
		double z5 = 0;
		double z6 = 0;
		double z7 = 0;
		double z8 = 0;
		try {
			z1 = grid.GetTile(x-1, y-1).getTotalFood();
			z2 = grid.GetTile(x, y-1).getTotalFood();
		 	z3 = grid.GetTile(x+1, y-1).getTotalFood();
			z4 = grid.GetTile(x-1, y).getTotalFood();
			z5 = grid.GetTile(x+1, y).getTotalFood();
			z6 = grid.GetTile(x-1, y+1).getTotalFood();
			z7 = grid.GetTile(x, y+1).getTotalFood();
			z8 = grid.GetTile(x+1, y+1).getTotalFood();
		} catch(Exception ex) {
			
		}
	
		int orgXSpeed = e.getOrigXSpeed();
		int orgYSpeed = e.getOrigYSpeed();
		String largestValue = "z1";
		double largestFoodDeposit = z1;
		if(z2 >= largestFoodDeposit) {
			largestValue = "z2";
			largestFoodDeposit = z2;
		} 
		if(z3 >= largestFoodDeposit) {
			largestValue = "z3";
			largestFoodDeposit = z3;
		} 
		if(z4 >= largestFoodDeposit) {
			largestValue = "z4";
			largestFoodDeposit = z4;
		} 
		if(z5 >= largestFoodDeposit) {
			largestValue = "z5";
			largestFoodDeposit = z5;
		} 
		if(z6 >= largestFoodDeposit) {
			largestValue = "z6";
			largestFoodDeposit = z6;
		} 
		if(z7 >= largestFoodDeposit) {
			largestValue = "z7";
			largestFoodDeposit = z7;
		} 
		if(z8 >= largestFoodDeposit) {
			largestValue = "z8";
			largestFoodDeposit = z8;
		} 
		//System.out.println(largestValue);
		//System.out.println(largestFoodDeposit);
		switch(largestValue) {
		case "z1":
			e.setxSpeed(-Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			e.setySpeed(-Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			break;
		case "z2":
			e.setxSpeed(0);
			e.setySpeed(-Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			break;
		case "z3":
			e.setxSpeed(Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			e.setySpeed(-Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			break;
		case "z4":
			e.setxSpeed(-Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			e.setySpeed(0);
			break;
		case "z5":
			e.setxSpeed(Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			e.setySpeed(0);
			break;
		case "z6":
			e.setxSpeed(-Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			e.setySpeed(Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			break;
		case "z7":
			e.setxSpeed(0);
			e.setySpeed(Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			break;
		case "z8":
			e.setxSpeed(Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			e.setySpeed(Math.sqrt((orgXSpeed*orgXSpeed)+(orgYSpeed*orgYSpeed)));
			break;
		}
	}
	
	
	//If the food is greater than or equal to 1, eat from it, if not (and if the tile is traversable) then send back true which will kill the entitiy
	public static boolean eatFood(int x, int y, TileGrid grid) {
		double food = grid.GetTile(x, y).getTotalFood();
		if(food >= 1) {
			grid.GetTile(x, y).setTotalFood(grid.GetTile(x, y).getTotalFood() - 1);
			return false;
		} else {
			if(grid.GetTile(x,y).getType().isTraversable()) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	//refreshes the food on a tile up to a maximum
	public static void replenishFood(TileGrid grid) {
		 for(int i = 0; i < config.getWidth()/config.getTilesize(); i++) {
			for(int j = 0; j < config.getHeight()/config.getTilesize(); j++) {
				double food = grid.GetTile(i, j).getTotalFood();
				TileType typeOfTile = grid.GetTile(i, j).getType();
				switch(typeOfTile) {
					case Mountains: 
						break;
					
					case Water: 
						if(food <= 1) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + grid.GetTile(i, j).getFoodRegen());
						} 
						break;
														
					case Dirt: 
						grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + grid.GetTile(i, j).getFoodRegen());
						if(food >= 80) {
							grid.GetTile(i, j).setTexture(QuickLoad("grass"));
							grid.GetTile(i, j).setType(TileType.Grass);
						}
						break;	
										
					case Grass: 
						if(food <= 100) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + grid.GetTile(i, j).getFoodRegen());
						} 
						if(food <= 80) {
							grid.GetTile(i, j).setTexture(QuickLoad("dirt"));
							grid.GetTile(i, j).setType(TileType.Dirt);
						}
						break;
										
					case Highlands:
						if(food <= 40) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + grid.GetTile(i, j).getFoodRegen());
						} 
						break;
					case Sand:
						if(food <= 25) {
							grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + grid.GetTile(i, j).getFoodRegen());
						} 
						break;
					
					default: 
						break;
					
				
				}
		 	}	
		}
	}
}
 