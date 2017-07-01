import java.awt.*; 
import java.util.List;
import java.util.*;

public class getTiles{
	private static LinkedList<Tile> tiles = new LinkedList<Tile>();
	private static ArrayList noiseNumbers = new ArrayList<Integer>();
	
	public LinkedList<Tile> getTileArray(int TILE_SIZE){
		generateNoise colours = new generateNoise();
		noiseNumbers = colours.getColours();
		int pos = 0;
		int x = 0;
		int y = 0;
		int colourValue = 0;
		for(int i = 0; i < 800; i = i + TILE_SIZE){
			for(int j = 0; j < 800; j = j + TILE_SIZE){
				colourValue = (int)noiseNumbers.get(pos);
				if(tiles.size() <= 40000){ 
					Tile newTile = new Tile(x,y,colourValue);
					tiles.add(newTile);
				}
				pos = pos + 1;
				x = x + TILE_SIZE;
			}
			y = y + TILE_SIZE;
			x = 0;
		}
		return tiles;
	}
}