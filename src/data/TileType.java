package data;

public enum TileType {
	
	Grass("grass", true, 100, 3), 
	Dirt("dirt", true, 100, 2), 
	Water("water", false, 30, 1), 
	Sand("sand", true, 30, 1), 
	Highlands("highland", true, 40, 1), 
	Mountains("mountain", false, 20, 1),
	Border("border", false, 0, 0);
	
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
