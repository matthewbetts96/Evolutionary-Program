package data;

public enum EntityType {
	Predator("predator", true, 200),
	Prey("prey", false, 200);
	
	String textureName;
	boolean isPredator;
	int maxHunger;
	String isFacing;

	EntityType(String textureName, boolean isPredator, int maxHunger){
		this.textureName = textureName;
		this.isPredator = isPredator;
		this.maxHunger = maxHunger;
	}

	public String getTextureName() {
		return textureName;
	}

	public boolean isPredator() {
		return isPredator;
	}

	public int getMaxHunger() {
		return maxHunger;
	}
}
