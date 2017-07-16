package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.QuickLoad;

import org.newdawn.slick.opengl.Texture;

public class Tile {
	private float x,y, width, height;
	private double foodRegen;
	private int totalFood;
	private Texture texture;
	private TileType type;
	
	public Tile(float x, float y, float width, float height, TileType type, int totalFood, double foodRegen) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = width;
		this.type = type;
		this.texture = QuickLoad(type.textureName);
		this.totalFood = totalFood;
		this.foodRegen = foodRegen;
	}
	
	public void Draw() {
		DrawQuadTex(texture, x, y, width, height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
	public double getFoodRegen() {
		return foodRegen;
	}

	public void setFoodRegen(float foodRegen) {
		this.foodRegen = foodRegen;
	}

	public int getTotalFood() {
		return totalFood;
	}

	public void setTotalFood(int totalFood) {
		this.totalFood = totalFood;
	}
	
	
}
