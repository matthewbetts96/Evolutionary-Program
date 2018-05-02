package data;

import static helpers.Artist.DrawQuadTex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Artist;
import helpers.Clock;
import helpers.Config;


public class TileGrid {
	private static Tile[][] map;
	
	private static int tileSize = Config.getSize();
	
	public TileGrid() {
		BufferedImage image = null;
	    File file = new File("noise.png");
	    try{
	        image = ImageIO.read(file);
	    } catch (IOException ex){
	        System.out.println("Error. Failed to load noise.png.");
	    }
	    map = new Tile[Config.getNoiseWidth()][Config.getNoiseHeight()]; 
	    
	    for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				int clr = image.getRGB(i,j);
				int colour = (clr & 0x00ff0000) >> 16;
			
				if(i <= 2 || j <= 2) {
					map[i][j] = new Tile(i * tileSize, j * tileSize, TileType.Mountains);
				} else if(i >= Config.getNoiseWidth() - 20 || j >= Config.getNoiseWidth() - 3) {
					map[i][j] = new Tile(i * tileSize, j * tileSize, TileType.Mountains);
				}	else if(colour > 170){
					map[i][j] = new Tile(i * tileSize, j * tileSize, TileType.Water);
	            } else if(colour > 160){
	            	map[i][j] = new Tile(i * tileSize, j * tileSize, TileType.Sand);	
	            } else if(colour > 95){
	            	map[i][j] = new Tile(i * tileSize, j * tileSize, TileType.Grass);	
				} else if(colour > 70){
					map[i][j] = new Tile(i * tileSize, j * tileSize, TileType.Highlands);	
	            } else {
	            	map[i][j] = new Tile(i * tileSize, j * tileSize, TileType.Mountains);	
	            }		
				
			}
		}
	}
	
	public static void Draw(TileGrid grid) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				Tile t = map[i][j];
				
				if(grid.GetTile(i, j).getCreaturesOnTile().size() >= 2) {
					EntityInteraction.interact(grid, i, j);
				}
	
				if(Clock.ticksSinceGameStart() % 5 == 0) {
					updateFood(grid, i, j);
				}
				
				grid.GetTile(i, j).clearEntityList();
				
				//Finally (re)draw the tile
				DrawQuadTex(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
			}
		}
	}
	
	private static void updateFood(TileGrid grid, int i, int j) {
		int food = grid.GetTile(i, j).getCurrentFood();
		TileType typeOfTile = grid.GetTile(i, j).getType();
		
		switch(typeOfTile) {
			case Mountains:
				break;
			case Water:
				break;
			case Highlands:
				if(food < typeOfTile.getMaxfood()) {
					grid.GetTile(i, j).setCurrentFood(grid.GetTile(i, j).getCurrentFood() + typeOfTile.getFoodRegen());
				}
				break;
			case Grass:
				if(food < typeOfTile.getMaxfood()) {
					grid.GetTile(i, j).setCurrentFood(grid.GetTile(i, j).getCurrentFood() + typeOfTile.getFoodRegen());
				}
				if(food <= 60) {
					grid.GetTile(i, j).setType(TileType.Dirt);
					grid.GetTile(i, j).setTexture(Artist.getDirt());
				}
				break;
			case Dirt:
				if(food < typeOfTile.getMaxfood()) {
					grid.GetTile(i, j).setCurrentFood(grid.GetTile(i, j).getCurrentFood() + typeOfTile.getFoodRegen());
				}
				
				if(food > 70) {
					grid.GetTile(i, j).setType(TileType.Grass);
					grid.GetTile(i, j).setTexture(Artist.getGrass());
				}
				break;
			case Sand:
				if(food < typeOfTile.getMaxfood()) {
					grid.GetTile(i, j).setCurrentFood(grid.GetTile(i, j).getCurrentFood() + typeOfTile.getFoodRegen());
				}
				break;
			default:
				System.out.println("Error in replenish food, unknown tiletype = "+ typeOfTile);
				break;
		}
	} 
	
	public void SetTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * Config.getSize(), yCoord * Config.getSize(), type);
	}
	
	public Tile GetTile(int xCoord, int yCoord) {
		return map[xCoord][yCoord];
	}
}
