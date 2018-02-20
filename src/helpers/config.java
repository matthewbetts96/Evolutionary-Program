package helpers;

public class Config {
	
	//DO NOT CHANGE MAP SIZE OR TILE SIZE RIGHT NOW, IT MIGHT BREAK THE UI AND/OR THE WHOLE SIM
	private static int width = 1000;
	private static int height = 1000;
	private static int size = 10; //size of creatures and tiles
	/*********************************************************/
	
	private static int featureSize = 3; //How "featureful" the map will be, the lower it is the more wacky the map gets
	private static int entityNumber = 100;
	private static int startMultiplier = 1;
	private static int noiseWidth = width/size;
	private static int noiseHeight = height/size;
	
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Config.width = width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Config.height = height;
	}
	public static int getSize() {
		return size;
	}
	public static void setSize(int size) {
		Config.size = size;
	}
	public static int getFeatureSize() {
		return featureSize;
	}
	public static void setFeatureSize(int featureSize) {
		Config.featureSize = featureSize;
	}
	public static int getEntityNumber() {
		return entityNumber;
	}
	public static void setEntityNumber(int entityNumber) {
		Config.entityNumber = entityNumber;
	}
	public static int getStartMultiplier() {
		return startMultiplier;
	}
	public static void setStartMultiplier(int startMultiplier) {
		Config.startMultiplier = startMultiplier;
	}
	public static int getNoiseWidth() {
		return noiseWidth;
	}
	public static void setNoiseWidth(int noiseWidth) {
		Config.noiseWidth = noiseWidth;
	}
	public static int getNoiseHeight() {
		return noiseHeight;
	}
	public static void setNoiseHeight(int noiseHeight) {
		Config.noiseHeight = noiseHeight;
	}
}
