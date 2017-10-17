package helpers;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import data.Entity;
import data.OpenSimplexNoise;
import data.Spawn;
import data.TileGrid;
import data.TileType;

public class General {
	
	private static final int WIDTH = config.getWidth();
	private static final int HEIGHT = config.getHeight();
	private static final int TILE_SIZE = config.getTilesize();
	private static final double FEATURE_SIZE = config.getFeaturesize();
	
	public static void BeginSession() {
		Display.setTitle("Evolutionary Simulation");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0 , 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		int rand = new Random().nextInt(1000);
		generateNoise(rand);
	}
	
	public static void generateNoise(int rand) {
		int noiseHeight = HEIGHT/TILE_SIZE;
		int noiseWidth = WIDTH/TILE_SIZE;
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
				double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, rand);
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
	
	//If it's the first loop remove all entities that have spawned on mountains or water
	public static void firstTimeCleanUp(TileGrid grid){
		ArrayList<Entity> entityList = new ArrayList<Entity>();
		entityList = Spawn.getEntityList();
		for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
			Entity e = iterator.next();
			int xPos = (int)e.getX()/config.getTilesize();
			int yPos = (int)e.getY()/config.getTilesize();
			if(grid.GetTile(xPos, yPos).getType() == TileType.Mountains || grid.GetTile(xPos, yPos).getType() == TileType.Water) {
				iterator.remove();
			}
		}
		Spawn.setEntityList(entityList);
	}
}
