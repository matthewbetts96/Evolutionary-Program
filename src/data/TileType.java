package data;

public enum TileType {
	
	Grass("grass", true, 100, 5), 
	Dirt("dirt", true, 100, 4), 
	Water("water", false, 0, 0), 
	Sand("sand", true, 60, 2), 
	Highlands("highland", true, 40, 3), 
	Mountains("mountain", false, 0, 0);
	
	String textureName;
	boolean traversable;
	int maxFood, foodRegen;
	
	TileType(String textureName, boolean traversable, int maxFood, int foodRegen){
		this.textureName = textureName;
		this.traversable = traversable;
		this.maxFood = maxFood;
		this.foodRegen = foodRegen;
	}

	public String getTextureName() {
		return textureName;
	}

	public boolean isTraversable() {
		return traversable;
	}
	
	public int getMaxfood() {
		return maxFood;
	}
	
	public int getFoodRegen() {
		return foodRegen;
	}
}
