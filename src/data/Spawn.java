package data;

import static helpers.Artist.QuickLoad;

import java.util.ArrayList;

import helpers.AI;
import helpers.config;

public class Spawn {
	private ArrayList<Entity> entityList;
	
	public Spawn() {
		entityList = new ArrayList<Entity>();
	}
	
	//Need to add method to remove entities that are on titles that are mountain/water at the start of the sim
	public void Update() {
		for(Entity e: entityList) {
			AI.doAIStuff(e);
			e.Update();
			e.Draw();
		}
	}
	
	public void SpawnEntities() {
		int Xrange = (100 - 1) + 1; 
		int Yrange = (100 - 1) + 1; 
		int randX = (int)(Math.random() * Xrange) + 1;
		int randY = (int)(Math.random() * Yrange) + 1;
		int randYSpeed = (int)(Math.random()*2 + 1);
		int randXSpeed = (int)(Math.random()*2 + 1);
		entityList.add(new Entity(QuickLoad("Entity"), randX, randY, config.getTilesize(), config.getTilesize(), randYSpeed, randXSpeed));
	
	}
}
