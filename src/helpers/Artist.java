package helpers;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	
	private static Texture prey;
	private static Texture grass;
	private static Texture dirt;
	private static Texture sand;
	private static Texture water;
	private static Texture mountain;
	private static Texture highland;
	private static Texture border;
	private static Texture predator;
	
	//Not used right now, might use later
	//Draws a square at x,y coords
	public static void DrawQuad(float x, float y, float width, float height) {
		glBegin(GL_QUADS);
		glVertex2f(x,y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
		glEnd();
		
	}
	
	public static void DrawQuadTex(Texture tex, float x, float y, float width, float height) {
		tex.bind();
		glTranslatef(x, y, 0); 
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width,height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static void preLoadTextures() {
		prey = LoadTexture("res/prey.png", "PNG");
		grass = LoadTexture("res/grass.png", "PNG");
		dirt = LoadTexture("res/dirt.png", "PNG");
		sand = LoadTexture("res/sand.png", "PNG");
		water = LoadTexture("res/water.png", "PNG");
		mountain = LoadTexture("res/mountain.png", "PNG");
		highland = LoadTexture("res/highland.png", "PNG");
		border = LoadTexture("res/border.png", "PNG");
		predator = LoadTexture("res/predator.png", "PNG");
	}
	
	//Method for loading in the textures to memory 
	public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return tex;
	}
	
	public static Texture QuickLoad(String name) {
		Texture tex = null;
		switch(name) {
			case "prey":
				tex = prey;
				break;
			case "grass":
				tex = grass;
				break;
			case "dirt":
				tex = dirt;
				break;
			case "sand":
				tex = sand;
				break;
			case "water":
				tex = water;
				break;
			case "mountain":
				tex = mountain;
				break;
			case "highland":
				tex = highland;
				break;
			case "border":
				tex = border;
				break;	
		}
		return tex;
	}

	public static Texture getPrey() {
		return prey;
	}

	public static Texture getGrass() {
		return grass;
	}

	public static Texture getSand() {
		return sand;
	}

	public static Texture getWater() {
		return water;
	}

	public static Texture getMountain() {
		return mountain;
	}

	public static Texture getHighland() {
		return highland;
	}

	public static Texture getBorder() {
		return border;
	}

	public static Texture getPredator() {
		return predator;
	}
	
	public static Texture getDirt() {
		return dirt;
	}
		
}
