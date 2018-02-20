package data;

public class findBestTile implements Comparable<findBestTile>{
	private int tileScore;
	private String direction;
	
	public findBestTile(int tileScore, String direction){
		super();
		this.tileScore = tileScore;
		this.direction = direction;
	}
	
	public int getTileScore() {
		return tileScore;
	}
	
	public void setTileScore(int tileScore) {
		this.tileScore = tileScore;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	//Override the compareTo method so we can sort the tiles
	@Override
	public int compareTo(findBestTile compareTiles) {
		int compareQuantity = ((findBestTile)compareTiles).getTileScore();
		return compareQuantity - this.tileScore;
	}
}
