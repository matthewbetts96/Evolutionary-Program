package data;

import static helpers.Artist.QuickLoad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import helpers.AI;
import helpers.config;

public class Spawn {
	private ArrayList<Entity> entityList;
	private boolean first = true;
	private boolean isDead = false;
	
	public Spawn() {
		entityList = new ArrayList<Entity>();
	}
	
	//Need to add method to remove entities that are on titles that are mountain/water at the start of the sim
	public void Update(TileGrid grid) {
		for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
		    Entity e = iterator.next();
		    int x = (int)e.getX()/config.getTilesize();
			int y = (int)e.getY()/config.getTilesize();
			isDead = AI.doAIStuff(e,grid,x,y);
			e.Update();
			e.Draw();
			if(!first) {
				AI.setNewDirection(e,grid, x, y);
			}
		    if(first) {
				if(grid.GetTile(x, y).getType() == TileType.Mountains || grid.GetTile(x, y).getType() == TileType.Water) {
					iterator.remove();
				}
			} else if(isDead) {
		    	iterator.remove();
		    }
		    isDead = false;
		}
		first = false;
		AI.replenishFood(grid);
	}
	
	public void SpawnEntities() {
		Random r = new Random();
		int Low = -5;
		int High = 5;
		int x = r.nextInt(High-Low) + Low;
		int y = r.nextInt(High-Low) + Low;	

		int xPos = r.nextInt(config.getHeight()-(config.getTilesize()*2));
		int yPos = r.nextInt(config.getHeight()-(config.getTilesize()*2));
		//new Entity(Texture, xPos, yPos, entity Height, entity Width, x speed, y speed, original x speed, original y speed)
		//we keep the original speeds so we can refence them when changing the entities movement later on
		entityList.add(new Entity(QuickLoad("Entity"), xPos, yPos, config.getTilesize(), config.getTilesize(), x, y, x, y));	
	}
}













