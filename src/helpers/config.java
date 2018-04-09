package helpers;

public class Config {
	
	
	private static int width = 1000;
	private static int height = 1000;
	private static int size = 10; //size of creatures and tiles 
	
	private static int featureSize = 20; //How "featureful" the map will be, the lower it is the more wacky the map gets
	private static int entityNumber = 200; //Number of starting creatures
	private static int startMultiplier = 1;
	private static int noiseWidth = width/size;
	private static int noiseHeight = height/size;

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
	public static int getNoiseWidth() {
		return noiseWidth;
	}
	public static int getNoiseHeight() {
		return noiseHeight;
	}
}
