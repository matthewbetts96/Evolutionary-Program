package helpers;

import java.util.Random;

import data.Entity;
import data.TileGrid;
import data.TileType;

public class AI {
	
	public static TileGrid grid = new TileGrid();
	public static Random random = new Random();
	
	public static void doAIStuff(Entity e) {
		int x = (int) e.getX()/config.getTilesize(); //Gets x, y coords
		int y = (int) e.getY()/config.getTilesize();
		int XSpeed = (int) e.getXSpeed();
		int YSpeed = (int) e.getYSpeed();
		checkBoundaries(e, x, y, XSpeed, YSpeed);
		noImpassableTiles(e, x, y, XSpeed, YSpeed);
		checkSurroundingTiles(e, x, y);
	}
	
	public static void noImpassableTiles(Entity e, int x, int y, int xSpeed, int ySpeed) {
		if(grid.GetTile(x,y).getType() == TileType.Mountains) {
			e.setXSpeed(xSpeed * -1);
			e.setYSpeed(ySpeed * -1);
		} else if(grid.GetTile(x,y).getType() == TileType.Water) {
			e.setXSpeed(xSpeed * -1);
			e.setYSpeed(ySpeed * -1);
		}
	}
	
	public static void checkBoundaries(Entity e, int x, int y, int xSpeed, int ySpeed) {
		if(e.getX() < 1) {
			e.setXSpeed(xSpeed * -1);
			e.setYSpeed(ySpeed * -1);
		} else if (e.getY() < 1){ 
			e.setXSpeed(xSpeed * -1);
			e.setYSpeed(ySpeed * -1);
		} else if(e.getX() >= config.getWidth() - config.getTilesize()) {
			e.setXSpeed(xSpeed * -1);
			e.setYSpeed(ySpeed * -1);
		} else if((e.getY() >= config.getHeight() - config.getTilesize())){
			e.setXSpeed(xSpeed * -1);
			e.setYSpeed(ySpeed * -1);
		}
	}
	
	//this needs redoing
	public static void checkSurroundingTiles(Entity e, int x, int y) {	
		/*
		 * e = the entity 
		 * 
		 * z1 z2 z3
		 * z4 e  z5
		 * z6 z7 z8  
		 * */
		int z1 = -1;
		int z2 = -1;
		int z3 = -1;
		int z4 = -1;
		int z5 = -1;
		int z6 = -1;
		int z7 = -1;
		int z8 = -1;
		System.out.println("x = "+ x + ". y =  " +y);
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
		
		System.out.println(z1 + " " + z2 + " " + z3+ " " +z4+ " " +z5+ " " +z6+ " " +z7+ " " +z8);
		System.out.println("___________________");
	}
	
}
