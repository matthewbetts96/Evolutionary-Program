package data;

public enum EntityType {
	Predator("Predator", true, 250),
	Prey("prey", false, 100);
	
	String textureName;
	boolean isPredator;
	int maxHunger;
	
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