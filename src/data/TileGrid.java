package data;

import static helpers.Artist.DrawQuadTex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TileGrid {
	public Tile[][] map;
	
	public TileGrid() {
		BufferedImage image = null;
        File file = new File("noise.png");
        try{
            image = ImageIO.read(file);
        } catch (IOException ex){
            System.out.println("IOException caught -generateNoise -getColours");
        }
        
		map = new Tile[320][240];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				int clr = image.getRGB(i,j);
				int red   = (clr & 0x00ff0000) >> 16;
				if(red > 170){
					map[i][j] = new Tile(i * 4, j * 4, 4, 4, TileType.Mountains); 	//Mountains
	            } else if(red > 125){
	            	map[i][j] = new Tile(i * 4, j * 4, 4, 4, TileType.Dirt);	//Highlands
				} else if(red > 75){
					map[i][j] = new Tile(i * 4, j * 4, 4, 4, TileType.Grass);	//Grasslands
	            } else {
	            	map[i][j] = new Tile(i * 4, j * 4, 4, 4, TileType.Water);	//Water
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
	
}
