import java.util.Random;

public class Tile{
	
	private int xPos;
	private int yPos;
	private String terrainType;
	private int totalFood;
	private int foodRegen;
	Random rand = new Random();
	
	public Tile(){
		System.out.print("Tile()");
	}
	
	public Tile(int x, int y, int iTerrainType){
		xPos = x;
		yPos = y;
		terrainType = Integer.toString(iTerrainType); 
		totalFood = rand.nextInt(10);
		foodRegen = 1;
	}
	
	public Tile(Object obj){
		this.xPos = xPos;
		this.yPos = yPos;
		this.terrainType = terrainType;
		this.totalFood = totalFood;
		this.foodRegen = foodRegen;
	}
	
	public int getXPos(){
		return xPos;
	}
	public int getYPos(){
		return yPos;
	}
	public String getTerrainType(){
		return terrainType;
	}
	public int getTotalFood(){
		return totalFood;
	}
	public int getFoodRegen(){
		return foodRegen;
	}
	
}