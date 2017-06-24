public class tile{
	private int x;
	private int y;
	private String terrainType;
	private int totalFood;
	private int foodRegen;
	
	public tile(){
	}
	
	public tile(int id){
		
	}
	
	public tile(Object obj){
		this.x = x;
		this.y = y;
		this.terrainType = terrainType;
		this.totalFood = totalFood;
		this.foodRegen = foodRegen;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public String getTerrainType(){
		return terrainType;
	}
	public int getTotalFood(){
		return totalFood;
	}
	
}