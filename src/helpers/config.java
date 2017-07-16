package helpers;

public class config {
	
	private static final int width = 320;
	private static final int height = 320;
	private static final int tileSize = 16; //this is going to be the same as the size of the entities
	private static final int noiseWidth = width/tileSize;
	private static final int noiseheight = width/tileSize;
	private static final int entityNumber = 10;

	
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	public static int getTilesize() {
		return tileSize;
	}
	public static int getNoisewidth() {
		return noiseWidth;
	}
	public static int getNoiseheight() {
		return noiseheight;
	}
	public static int getEntitynumber() {
		return entityNumber;
	}
}
