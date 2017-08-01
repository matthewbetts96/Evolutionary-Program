package data;

import static helpers.Artist.DrawQuadTex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.config;

public class TileGrid {
	public Tile[][] map;
	
	private final int TILE_SIZE = config.getTilesize(); 
	
	public TileGrid() {
		BufferedImage image = null;
        File file = new File("noise.png");
        try{
            image = ImageIO.read(file);
        } catch (IOException ex){
            System.out.println("IOException caught -generateNoise -getColours");
        }        
		map = new Tile[config.getNoisewidth()][config.getNoiseheight()]; 
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				int clr = image.getRGB(i,j);
				int red   = (clr & 0x00ff0000) >> 16;
				if(i == 0 || i == 1 || j == 0 || j == 1) {
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water, -1, 0);
				} else if(i >= config.getNoiseheight() - 2 || j >= config.getNoiseheight() - 2) {
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water, -1, 0);
				} else if(red > 170){
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water, -1, 0);
	            } else if(red > 160){
	            	map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Sand, 10, 0);	
	            } else if(red > 95){
	            	map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass, 100, 0.5);	
				} else if(red > 65){
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Highlands, 20, 0.5);	
	            } else {
	            	map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Mountains, -1, 0);	
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
	
	public void SetTile(int xCoord, int yCoord, TileType type, int totalFood, int foodRegen) {
		map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE, type, totalFood, foodRegen);
	}
	
	public Tile GetTile(int xCoord, int yCoord) {
		return map[xCoord][yCoord];
	}
}
