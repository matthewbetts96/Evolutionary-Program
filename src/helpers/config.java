package helpers;

public class Config {
	private static int width = 960;
	private static int height = 960;
	private static int size = 40; //size of creatures and tiles
	private static int featureSize = 20; //How "featureful" the map will be, the lower it is the more wacky the map gets
	private static int entityNumber = 50;
	private static int startMultiplier = 1;
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
	public static int getSize() {
		return size;
	}
	
	public static int getFeatureSize() {
		return featureSize;
	}
	
	public static int getEntityNumber() {
		return entityNumber;
	}
	
	public static int getStartMultiplier() {
		return startMultiplier;
	}
	
}
