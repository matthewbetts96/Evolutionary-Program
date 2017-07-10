package data;

public enum TileType {
	
	Grass("grass", true), Dirt("dirt", true), Water("water", false), Mountains("mountain", false);
	
	String textureName;
	boolean traversable;
	
	TileType(String textureName, boolean traversable){
		this.textureName = textureName;
		this.traversable = traversable;
	}
}
