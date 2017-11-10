package data;

public class findBestTile implements Comparable<findBestTile>{
	private int xCoord;
	private int yCoord;
	private int currentFood;
	private String direction;
	
	public findBestTile(int xCoord, int yCoord, int currentFood, String direction){
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.currentFood = currentFood;
		this.direction = direction;
	}
	
	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getCurrentFood() {
		return currentFood;
	}

	public void setCurrentFood(int currentFood) {
		this.currentFood = currentFood;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/*I really need someone to explain to 
	 * me how/why this works
	 */
	@Override
	public int compareTo(findBestTile compareTiles) {
		int compareQuantity = ((findBestTile)compareTiles).getCurrentFood();
		return compareQuantity - this.currentFood;
	}
}
