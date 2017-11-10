package data;

import static helpers.Artist.DrawQuadTex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Config;

public class TileGrid {
	private static Tile[][] map;
	
	public TileGrid() {
		BufferedImage image = null;
	    File file = new File("noise.png");
	    try{
	        image = ImageIO.read(file);
	    } catch (IOException ex){
	        System.out.println("Error. Failed to load noise.png.");
	    } 
	    int noiseWidth = (int)Config.getWidth()/Config.getSize();
	    int noiseHeight = (int)Config.getHeight()/Config.getSize();
		map = new Tile[noiseWidth][noiseHeight]; 
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				int clr = image.getRGB(i,j);
				int colour = (clr & 0x00ff0000) >> 16;
			
				if(i == 0 || i == 1 || j == 0 || j == 1) {
					map[i][j] = new Tile(i * Config.getSize(), j * Config.getSize(), TileType.Border);
				} else if(i >= noiseWidth - 2 || j >= noiseWidth - 2) {
					map[i][j] = new Tile(i * Config.getSize(), j * Config.getSize(), TileType.Border);
					/* 
					 * Fist two if/else if define the border
					 * The rest define the map 
					 */
				} else if(colour > 170){
					map[i][j] = new Tile(i * Config.getSize(), j * Config.getSize(), TileType.Water);
	            } else if(colour > 160){
	            	map[i][j] = new Tile(i * Config.getSize(), j * Config.getSize(), TileType.Sand);	
	            } else if(colour > 95){
	            	map[i][j] = new Tile(i * Config.getSize(), j * Config.getSize(), TileType.Grass);	
				} else if(colour > 85){
					map[i][j] = new Tile(i * Config.getSize(), j * Config.getSize(), TileType.Highlands);	
	            } else {
	            	map[i][j] = new Tile(i * Config.getSize(), j * Config.getSize(), TileType.Mountains);	
	            }		
			}
		}
	}
	
	public void Draw() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				Tile t = map[i][j];
				DrawQuadTex(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
			}
		}
	}
	
	public void SetTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * Config.getSize(), yCoord * Config.getSize(), type);
	}
	
	public Tile GetTile(int xCoord, int yCoord) {
		return map[xCoord][yCoord];
	}
}
