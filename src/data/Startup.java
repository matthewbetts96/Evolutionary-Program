package data;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import helpers.Config;


public class Startup {
	
	private static int width = Config.getWidth();
	private static int height = Config.getHeight();
	private static double featureSize = Config.getFeatureSize();
	
	public static void createSession() {
		Display.setTitle("Evolutionary Simulation 2");
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0 , 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		int rand = new Random().nextInt(1000);
		generateNoise(rand);
	}
	
	public static void generateNoise(int rand) {
		int noiseHeight = Config.getNoiseHeight();
		int noiseWidth = Config.getNoiseWidth();
		try{
			File file = new File("noise.png");
			if(file.delete()){
				System.out.println("Resetting " + file.getName());
			}else{
				System.out.println("Delete operation has failed. This is not a problem if it is the first run.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		OpenSimplexNoise noise = new OpenSimplexNoise();
		BufferedImage image = new BufferedImage(noiseWidth, noiseHeight, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < noiseHeight; y++)
			{
			for (int x = 0; x < noiseWidth; x++)
			{
				double value = noise.eval(x / featureSize, y / featureSize, rand);
				int rgb = 0x010101 * (int)((value + 1) * 127.5);
				image.setRGB(x, y, rgb);
			}
		}
		try{
			ImageIO.write(image, "png", new File("noise.png"));
			System.out.println("Map Generated!");
		} catch (IOException ex){
			System.out.println("IOException caught -generateNoise -generateNoise");
		}
    }
	
	/*//If it's the first loop remove all entities that have spawned on mountains or water
	public static void firstTimeCleanUp(TileGrid grid){
		ArrayList<Entity> entityList = Spawn.getEntityList();
		int i = 0;
		for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
			Entity e = iterator.next();
			int xPos = (int)e.getX()/Config.getSize();
			int yPos = (int)e.getY()/Config.getSize();
			if((grid.GetTile(xPos, yPos).getType() == TileType.Mountains || grid.GetTile(xPos, yPos).getType() == TileType.Water) ||  grid.GetTile(xPos, yPos).getType() == TileType.Border) {
				i++;
				iterator.remove();
			}
		}
		System.out.println(i + " entities removed for being on impassible terrain.");
		Spawn.setEntityList(entityList);
	}*/
}
