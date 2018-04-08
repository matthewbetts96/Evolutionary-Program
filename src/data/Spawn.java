package data;

import java.util.ArrayList;

public class Spawn {
	
	//This is the main arraylist where all the creatures are stored
	private static ArrayList<Entity> entityList = new ArrayList<Entity>();
	
	//Inital spawning method
	public void SpawnEntities() {
		entityList.add(new Entity(EntityType.Prey));
	}
	
	public static ArrayList<Entity> getEntityList() {
		return entityList;
	}

	public static void setEntityList(ArrayList<Entity> entityList) {
		Spawn.entityList = entityList;
	}
}
