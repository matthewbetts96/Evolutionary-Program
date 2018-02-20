package data;

import java.util.ArrayList;

public class tilesInVision {
	
	private static String facingDirection;
	private static int tilescore;
	private static findBestTile[] surroundingTiles = new findBestTile[15];
	
	public static findBestTile[] getFacingTiles(Entity e, TileGrid grid, int x, int y){
		facingDirection = e.getIsFacing();
		
		switch(facingDirection)
		{
			case "north":
				north(e, grid, x, y);
				break;
				
			case "south":
				south(e, grid, x, y);
				break;
				
			case "east":
				east(e, grid, x, y);
				break;
				
			case "west":
				west(e, grid, x, y);
				break;
				
			case "northwest":
				northwest(e, grid, x, y);
				break;
				
			case "northeast":
				northeast(e, grid, x, y);
				break;
				
			case "southwest":
				southwest(e, grid, x, y);
				break;
				
			case "southeast":
				southeast(e, grid, x, y);
				break;
		}
		return surroundingTiles;
	}
	
	//Evaulates the 'score' of a tile based off of it's attractiveness to the 
	//current creature. Takes into account food, friends and possible enemies 
	//All possible 
	public static int evaluateCreaturesOnTile(int x, int y, TileGrid grid, Entity e) {
		int tileScore = 0;
		String entitySpecies = e.getSpecies().getTextureName();
		int numOfCreaturesOnTile = grid.GetTile(x, y).getCreaturesOnTile().size();
		ArrayList<Entity> creaturesOnTile = grid.GetTile(x, y).getCreaturesOnTile();

		//For each creature on the tile
		for(int i = 0; i < creaturesOnTile.size(); i++) {
			//Account for possible friends/foes
			if(creaturesOnTile.get(i).getSpecies().getTextureName() == entitySpecies) {
				tileScore = tileScore + 100;
			} else {
				tileScore = tileScore - 100;
			}
		}
		
		//Account for the "crowdedness" of a tile
		if(numOfCreaturesOnTile >= 3) {
			tileScore = tileScore - 100;
		}
			
		//Ajust score based off of food level
		tileScore = tileScore + grid.GetTile(x, y).getTotalFood();
		return tileScore;
	
	}
	
	//Source image for tile vision cones 
	//https://puu.sh/zaQQp/0ee71beebd.png
	public static findBestTile[] north(Entity e, TileGrid grid, int x, int y) {
		
		tilescore = grid.GetTile(x-1, y-1).getTotalFood() + evaluateCreaturesOnTile(x-1, y-1, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x, y-1).getTotalFood() + evaluateCreaturesOnTile(x, y-1, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x+1, y-1).getTotalFood() + evaluateCreaturesOnTile(x+1, y-1, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "northeast");
		
		tilescore = grid.GetTile(x-2, y-2).getTotalFood() + evaluateCreaturesOnTile(x-2, y-2, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-1, y-2).getTotalFood() + evaluateCreaturesOnTile(x-1, y-2, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x, y-2).getTotalFood() + evaluateCreaturesOnTile(x, y-2, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x+1, y-2).getTotalFood() + evaluateCreaturesOnTile(x+1, y-2, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+2, y-2).getTotalFood() + evaluateCreaturesOnTile(x+2, y-2, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "northeast");
		
		tilescore = grid.GetTile(x+3, y-3).getTotalFood() + evaluateCreaturesOnTile(x-3, y-3, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x+2, y-3).getTotalFood() + evaluateCreaturesOnTile(x-2, y-3, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x+1, y-3).getTotalFood() + evaluateCreaturesOnTile(x-1, y-3, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x, y-3).getTotalFood() + evaluateCreaturesOnTile(x, y-3, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x+1, y-3).getTotalFood() + evaluateCreaturesOnTile(x+1, y-3, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x+2, y-3).getTotalFood() + evaluateCreaturesOnTile(x+2, y-3, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+3, y-3).getTotalFood() + evaluateCreaturesOnTile(x+3, y-3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "northeast");
		return surroundingTiles;
	} 
	public static findBestTile[] south(Entity e, TileGrid grid, int x, int y) {
		tilescore = grid.GetTile(x-1, y+1).getTotalFood() + evaluateCreaturesOnTile(x-1, y+1, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x, y+1).getTotalFood() + evaluateCreaturesOnTile(x, y+1, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x+1, y+1).getTotalFood() + evaluateCreaturesOnTile(x+1, y+1, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "northeast");
		
		tilescore = grid.GetTile(x-2, y+2).getTotalFood() + evaluateCreaturesOnTile(x-2, y+2, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-1, y+2).getTotalFood() + evaluateCreaturesOnTile(x-1, y+2, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x, y+2).getTotalFood() + evaluateCreaturesOnTile(x, y+2, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x+1, y+2).getTotalFood() + evaluateCreaturesOnTile(x+1, y+2, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+2, y+2).getTotalFood() + evaluateCreaturesOnTile(x+2, y+2, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "northeast");
		
		tilescore = grid.GetTile(x+3, y+3).getTotalFood() + evaluateCreaturesOnTile(x-3, y+3, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x+2, y+3).getTotalFood() + evaluateCreaturesOnTile(x-2, y+3, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x+1, y+3).getTotalFood() + evaluateCreaturesOnTile(x-1, y+3, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x, y+3).getTotalFood() + evaluateCreaturesOnTile(x, y+3, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x+1, y+3).getTotalFood() + evaluateCreaturesOnTile(x+1, y+3, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x+2, y+3).getTotalFood() + evaluateCreaturesOnTile(x+2, y+3, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+3, y+3).getTotalFood() + evaluateCreaturesOnTile(x+3, y+3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "northeast");
		return surroundingTiles;
	} 
	public static findBestTile[] east(Entity e, TileGrid grid, int x, int y) {
		tilescore = grid.GetTile(x+1, y).getTotalFood() + evaluateCreaturesOnTile(x+1, y, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+1, y-1).getTotalFood() + evaluateCreaturesOnTile(x+1, y-1, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "southeast");
		tilescore = grid.GetTile(x+1, y+1).getTotalFood() + evaluateCreaturesOnTile(x+1, y+1, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "northeast");
		
		tilescore = grid.GetTile(x+2, y-2).getTotalFood() + evaluateCreaturesOnTile(x+2, y-2, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x+2, y-1).getTotalFood() + evaluateCreaturesOnTile(x+2, y-1, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x+2, y).getTotalFood() + evaluateCreaturesOnTile(x+2, y, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+2, y+1).getTotalFood() + evaluateCreaturesOnTile(x+2, y+1, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+2, y+2).getTotalFood() + evaluateCreaturesOnTile(x+2, y+2, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "northeast");
		
		tilescore = grid.GetTile(x+3, y-3).getTotalFood() + evaluateCreaturesOnTile(x+3, y-3, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x+3, y-2).getTotalFood() + evaluateCreaturesOnTile(x+3, y-2, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x+3, y-1).getTotalFood() + evaluateCreaturesOnTile(x+3, y-1, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+3, y).getTotalFood() + evaluateCreaturesOnTile(x+3, y, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+3, y+1).getTotalFood() + evaluateCreaturesOnTile(x+3, y+1, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+3, y+2).getTotalFood() + evaluateCreaturesOnTile(x+3, y+2, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+3, y+3).getTotalFood() + evaluateCreaturesOnTile(x+3, y+3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "northeast");
		return surroundingTiles;
	} 
	public static findBestTile[] west(Entity e, TileGrid grid, int x, int y) {
		tilescore = grid.GetTile(x-1, y).getTotalFood() + evaluateCreaturesOnTile(x-1, y, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-1, y-1).getTotalFood() + evaluateCreaturesOnTile(x-1, y-1, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-1, y+1).getTotalFood() + evaluateCreaturesOnTile(x-1, y+1, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "southwest");
		
		tilescore = grid.GetTile(x-2, y-2).getTotalFood() + evaluateCreaturesOnTile(x-2, y-2, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-2, y-1).getTotalFood() + evaluateCreaturesOnTile(x-2, y-1, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-2, y).getTotalFood() + evaluateCreaturesOnTile(x-2, y, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-2, y+1).getTotalFood() + evaluateCreaturesOnTile(x-2, y+1, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-2, y+2).getTotalFood() + evaluateCreaturesOnTile(x-2, y+2, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "southwest");
		
		tilescore = grid.GetTile(x-3, y-3).getTotalFood() + evaluateCreaturesOnTile(x-3, y-3, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-3, y-2).getTotalFood() + evaluateCreaturesOnTile(x-3, y-2, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-3, y-1).getTotalFood() + evaluateCreaturesOnTile(x-3, y-1, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-3, y).getTotalFood() + evaluateCreaturesOnTile(x-3, y, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-3, y+1).getTotalFood() + evaluateCreaturesOnTile(x-3, y+1, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-3, y+2).getTotalFood() + evaluateCreaturesOnTile(x-3, y+2, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-3, y+3).getTotalFood() + evaluateCreaturesOnTile(x-3, y+3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "southwest");
		return surroundingTiles;
	} 
	public static findBestTile[] northwest(Entity e, TileGrid grid, int x, int y) {
		tilescore = grid.GetTile(x, y-1).getTotalFood() + evaluateCreaturesOnTile(x, y-1, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x, y-2).getTotalFood() + evaluateCreaturesOnTile(x, y-2, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x, y-3).getTotalFood() + evaluateCreaturesOnTile(x, y-3, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "north");
		
		tilescore = grid.GetTile(x-1, y).getTotalFood() + evaluateCreaturesOnTile(x-2, y, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-1, y-1).getTotalFood() + evaluateCreaturesOnTile(x-2, y-1, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-1, y-2).getTotalFood() + evaluateCreaturesOnTile(x-2, y-2, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-1, y-3).getTotalFood() + evaluateCreaturesOnTile(x-2, y-3, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "north");
		
		tilescore = grid.GetTile(x-2, y).getTotalFood() + evaluateCreaturesOnTile(x-2, y, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-2, y-1).getTotalFood() + evaluateCreaturesOnTile(x-3, y-1, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-2, y-2).getTotalFood() + evaluateCreaturesOnTile(x-3, y-2, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-2, y-3).getTotalFood() + evaluateCreaturesOnTile(x-3, y-3, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "northwest");
		
		tilescore = grid.GetTile(x-3, y).getTotalFood() + evaluateCreaturesOnTile(x-3, y, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-3, y-1).getTotalFood() + evaluateCreaturesOnTile(x-3, y-1, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-3, y-2).getTotalFood() + evaluateCreaturesOnTile(x-3, y-2, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "northwest");
		tilescore = grid.GetTile(x-3, y-3).getTotalFood() + evaluateCreaturesOnTile(x-3, y-3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "northwest");
		return surroundingTiles;
	} 
	public static findBestTile[] northeast(Entity e, TileGrid grid, int x, int y) {
		tilescore = grid.GetTile(x, y-1).getTotalFood() + evaluateCreaturesOnTile(x, y-1, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x, y-2).getTotalFood() + evaluateCreaturesOnTile(x, y-2, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "north");
		tilescore = grid.GetTile(x, y-3).getTotalFood() + evaluateCreaturesOnTile(x, y-3, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "north");
		
		tilescore = grid.GetTile(x+1, y).getTotalFood() + evaluateCreaturesOnTile(x+2, y, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+1, y-1).getTotalFood() + evaluateCreaturesOnTile(x+2, y-1, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+1, y-2).getTotalFood() + evaluateCreaturesOnTile(x+2, y-2, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+1, y-3).getTotalFood() + evaluateCreaturesOnTile(x+2, y-3, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "north");
		
		tilescore = grid.GetTile(x+2, y).getTotalFood() + evaluateCreaturesOnTile(x-2, y, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+2, y-1).getTotalFood() + evaluateCreaturesOnTile(x-3, y-1, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+2, y-2).getTotalFood() + evaluateCreaturesOnTile(x-3, y-2, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+2, y-3).getTotalFood() + evaluateCreaturesOnTile(x-3, y-3, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "northeast");
		
		tilescore = grid.GetTile(x+3, y).getTotalFood() + evaluateCreaturesOnTile(x+3, y, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+3, y-1).getTotalFood() + evaluateCreaturesOnTile(x+3, y-1, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+3, y-2).getTotalFood() + evaluateCreaturesOnTile(x+3, y-2, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "northeast");
		tilescore = grid.GetTile(x+3, y-3).getTotalFood() + evaluateCreaturesOnTile(x+3, y-3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "northeast");
		return surroundingTiles;
	} 
	public static findBestTile[] southwest(Entity e, TileGrid grid, int x, int y) {
		tilescore = grid.GetTile(x, y+1).getTotalFood() + evaluateCreaturesOnTile(x, y+1, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x, y+2).getTotalFood() + evaluateCreaturesOnTile(x, y+2, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x, y+3).getTotalFood() + evaluateCreaturesOnTile(x, y+3, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "south");
		
		tilescore = grid.GetTile(x-1, y).getTotalFood() + evaluateCreaturesOnTile(x-2, y, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-1, y+1).getTotalFood() + evaluateCreaturesOnTile(x-2, y+1, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-1, y+2).getTotalFood() + evaluateCreaturesOnTile(x-2, y+2, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-1, y+3).getTotalFood() + evaluateCreaturesOnTile(x-2, y+3, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "south");
		
		tilescore = grid.GetTile(x-2, y).getTotalFood() + evaluateCreaturesOnTile(x-2, y, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-2, y+1).getTotalFood() + evaluateCreaturesOnTile(x-3, y+1, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-2, y+2).getTotalFood() + evaluateCreaturesOnTile(x-3, y+2, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-2, y+3).getTotalFood() + evaluateCreaturesOnTile(x-3, y+3, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "southwest");
		
		tilescore = grid.GetTile(x-3, y).getTotalFood() + evaluateCreaturesOnTile(x-3, y, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-3, y+1).getTotalFood() + evaluateCreaturesOnTile(x-3, y+1, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "west");
		tilescore = grid.GetTile(x-3, y+2).getTotalFood() + evaluateCreaturesOnTile(x-3, y+2, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "southwest");
		tilescore = grid.GetTile(x-3, y+3).getTotalFood() + evaluateCreaturesOnTile(x-3, y+3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "southwest");
		return surroundingTiles;
	} 
	public static findBestTile[] southeast(Entity e, TileGrid grid, int x, int y) {
		tilescore = grid.GetTile(x, y+1).getTotalFood() + evaluateCreaturesOnTile(x, y+1, grid, e); 
		surroundingTiles[0] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x, y+2).getTotalFood() + evaluateCreaturesOnTile(x, y+2, grid, e); 
		surroundingTiles[1] = new findBestTile(tilescore, "south");
		tilescore = grid.GetTile(x, y+3).getTotalFood() + evaluateCreaturesOnTile(x, y+3, grid, e); 
		surroundingTiles[2] = new findBestTile(tilescore, "south");
		
		tilescore = grid.GetTile(x+1, y).getTotalFood() + evaluateCreaturesOnTile(x+2, y, grid, e); 
		surroundingTiles[3] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+1, y+1).getTotalFood() + evaluateCreaturesOnTile(x+2, y+1, grid, e); 
		surroundingTiles[4] = new findBestTile(tilescore, "southeast");
		tilescore = grid.GetTile(x+1, y+2).getTotalFood() + evaluateCreaturesOnTile(x+2, y+2, grid, e); 
		surroundingTiles[5] = new findBestTile(tilescore, "southeast");
		tilescore = grid.GetTile(x+1, y+3).getTotalFood() + evaluateCreaturesOnTile(x+2, y+3, grid, e); 
		surroundingTiles[6] = new findBestTile(tilescore, "south");
		
		tilescore = grid.GetTile(x+2, y).getTotalFood() + evaluateCreaturesOnTile(x-2, y, grid, e); 
		surroundingTiles[7] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+2, y+1).getTotalFood() + evaluateCreaturesOnTile(x-3, y+1, grid, e); 
		surroundingTiles[8] = new findBestTile(tilescore, "southeast");
		tilescore = grid.GetTile(x+2, y+2).getTotalFood() + evaluateCreaturesOnTile(x-3, y+2, grid, e); 
		surroundingTiles[9] = new findBestTile(tilescore, "southeast");
		tilescore = grid.GetTile(x+2, y+3).getTotalFood() + evaluateCreaturesOnTile(x-3, y+3, grid, e); 
		surroundingTiles[10] = new findBestTile(tilescore, "southeast");
		
		tilescore = grid.GetTile(x+3, y).getTotalFood() + evaluateCreaturesOnTile(x+3, y, grid, e); 
		surroundingTiles[11] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+3, y+1).getTotalFood() + evaluateCreaturesOnTile(x+3, y+1, grid, e); 
		surroundingTiles[12] = new findBestTile(tilescore, "east");
		tilescore = grid.GetTile(x+3, y+2).getTotalFood() + evaluateCreaturesOnTile(x+3, y+2, grid, e); 
		surroundingTiles[13] = new findBestTile(tilescore, "southeast");
		tilescore = grid.GetTile(x+3, y+3).getTotalFood() + evaluateCreaturesOnTile(x+3, y+3, grid, e); 
		surroundingTiles[14] = new findBestTile(tilescore, "southeast");
		return surroundingTiles;
	} 
}
