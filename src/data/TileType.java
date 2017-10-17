package data;

public enum TileType {
	
	Grass("grass", true), 
	Dirt("dirt", true), 
	Water("water", false), 
	Sand("sand", true), 
	Highlands("highland", true), 
	Mountains("mountain", false);
	
	String textureName;
	boolean traversable;
	
	TileType(String textureName, boolean traversable){
		this.textureName = textureName;
		this.traversable = traversable;
	}

	public String getTextureName() {
		return textureName;
	}

	public boolean isTraversable() {
		return traversable;
	}
}
