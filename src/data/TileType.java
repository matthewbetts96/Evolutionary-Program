package data;

public enum TileType {
	
	Grass("grass", true, 200, 4), 
	Dirt("dirt", true, 200, 3), 
	Water("water", false, 0, 0), 
	Sand("sand", true, 80, 3), 
	Highlands("highland", true, 100, 2), 
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
