package helpers;

public class config {
	
	private static int width = 1200;
	private static int height = 960;
	private static int tileSize = 6; //entities will be the size of the tiles
	private static int noiseWidth = width/tileSize;
	private static int noiseheight = height/tileSize;
	private static int featureSize = 24; //How "featureful" the map will be, the lower it is the more wacky the map gets
	private static int entityNumber = 5000;
	private static int startMultiplier = 1;
	
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
	
	public static int getFeaturesize() {
		return featureSize;
	}
	
	public static int getStartmultiplier() {
		return startMultiplier;
	}
}
	