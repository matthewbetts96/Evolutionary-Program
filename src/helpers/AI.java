package helpers;

import java.util.ArrayList;
import java.util.Random;

import static helpers.Artist.*;

import data.Entity;
import data.TileGrid;
import data.TileType;

public class AI {
	
	public static Random random = new Random();
	
	public static void doAIStuff(Entity e, TileGrid grid, int x, int y) {
		double XSpeed = e.getxSpeed();
		double YSpeed = e.getySpeed();
		noImpassableTiles(e, x, y, XSpeed, YSpeed, grid);
		//checkSurroundingTiles(e, x, y, grid);
		eatFood(x,y,grid);
	}
	
	//Stops entities moving on tiles that they shouldn't
	public static void noImpassableTiles(Entity e, int x, int y, double xSpeed, double ySpeed, TileGrid grid) {
		if(grid.GetTile(x,y).getType() == TileType.Mountains) {
			e.setxSpeed(xSpeed * -1);
			e.setySpeed(ySpeed * -1);
		} else if(grid.GetTile(x,y).getType() == TileType.Water) {
			e.setxSpeed(xSpeed * -1);
			e.setySpeed(ySpeed * -1);
		}
	}
	
	//this needs redoing
	public static void checkSurroundingTiles(Entity e, int x, int y, TileGrid grid) {	
		/*
		 * e = the entity 
		 * 
		 * z1 z2 z3
		 * z4 e  z5
		 * z6 z7 z8  
		 * 
		 * */
		
		double z1 = -1;
		double z2 = -1;
		double z3 = -1;
		double z4 = -1;
		double z5 = -1;
		double z6 = -1;
		double z7 = -1;
		double z8 = -1;
		//top middle
		if(e.getY() > config.getTilesize()*2) {
			z2 = grid.GetTile(x, y-1).getTotalFood();
		}
		//left middle
		if(e.getX() > config.getTilesize()*2) {
			z4 = grid.GetTile(x-1, y).getTotalFood();
		}
		//right middle
		if(e.getX() < config.getWidth() - config.getTilesize()) {
			z5 = grid.GetTile(x+1, y).getTotalFood();
		}
		//left middle
		if(e.getY() < config.getHeight() - config.getTilesize()){
			z7 = grid.GetTile(x, y+1).getTotalFood();
		}
		//top left 
		if(e.getY() > config.getTilesize()*2 && e.getX() > config.getTilesize()*2) {
			z1 = grid.GetTile(x-1, y-1).getTotalFood();			
		}
		//top right
		if(e.getY() > config.getTilesize()*2 && e.getX() < config.getWidth() - config.getTilesize()) {
			z3 = grid.GetTile(x+1, y-1).getTotalFood();
		}
		//bottom left
		if(e.getX() > config.getTilesize()*2 && e.getY() < config.getHeight() - config.getTilesize()) {
			z6 = grid.GetTile(x-1, y+1).getTotalFood();
		}
		//bottom right
		if(e.getX() < config.getWidth() - config.getTilesize() && e.getY() < config.getHeight() - config.getTilesize()) {
			z8 = grid.GetTile(x+1, y+1).getTotalFood();
		}
		
		System.out.println(z1 + " " + z2 + " " + z3 + " " + z4 + " " + z5 + " " + z6 + " " + z7 + " " + z8);

		

		/* 
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
		
	}
	
	public static void eatFood( int x, int y, TileGrid grid) {
		grid.GetTile(x, y).setTotalFood(grid.GetTile(x, y).getTotalFood() - 1);
	}
	
	//refreshes the food on a tile up to a maximum
	public static void replenishFood(TileGrid grid) {
		for(int i = 0; i < config.getWidth()/config.getTilesize(); i++) {
			for(int j = 0; j < config.getHeight()/config.getTilesize(); j++) {
				if(grid.GetTile(i, j).getType() != TileType.Mountains || grid.GetTile(i, j).getType() != TileType.Water) {
					double food = grid.GetTile(i, j).getTotalFood();
					if(food < 100) {
						grid.GetTile(i, j).setTotalFood(grid.GetTile(i, j).getTotalFood() + grid.GetTile(i, j).getFoodRegen());
						if(food > 85) {
							grid.GetTile(i, j).setTexture(QuickLoad("grass"));
							grid.GetTile(i, j).setType(TileType.Grass);
						} else if(food <= 85 && food > 65) {
							grid.GetTile(i, j).setTexture(QuickLoad("dirt"));
							grid.GetTile(i, j).setType(TileType.Dirt);
						} else if(food < 20) {
							grid.GetTile(i, j).setTexture(QuickLoad("sand"));
							grid.GetTile(i, j).setType(TileType.Sand);
							grid.GetTile(i, j).setFoodRegen(0);
						} 
					}
				}	
			}
		}
	}	
}

