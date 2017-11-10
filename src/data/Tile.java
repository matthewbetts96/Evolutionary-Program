package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.QuickLoad;

import java.util.ArrayList;

import helpers.Config;

public class Tile {
	private int x,y , width, height, totalFood, foodRegen;
	private Texture texture;
	private TileType type;
	private ArrayList<Entity> creaturesOnTile;
	
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.width = Config.getSize();
		this.height = Config.getSize();
		this.type = type;
		this.texture = QuickLoad(type.textureName);
		this.totalFood = type.maxFood;
		this.foodRegen = type.foodRegen;
		this.creaturesOnTile = new ArrayList<Entity>(); 
	}
	
	public void Draw() {
		DrawQuadTex(texture, x, y, width, height);
	}
		
	public void addEntity(Entity e) {
		creaturesOnTile.add(e);
	}
	
	public void clearEntityList() {
		creaturesOnTile.clear();
	} 
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTotalFood() {
		return totalFood;
	}

	public void setTotalFood(int totalFood) {
		this.totalFood = totalFood;
	}

	public int getFoodRegen() {
		return foodRegen;
	}

	public void setFoodRegen(int foodRegen) {
		this.foodRegen = foodRegen;
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

	public ArrayList<Entity> getCreaturesOnTile() {
		return creaturesOnTile;
	}

	public void setCreaturesOnTile(ArrayList<Entity> creaturesOnTile) {
		this.creaturesOnTile = creaturesOnTile;
	}
}
