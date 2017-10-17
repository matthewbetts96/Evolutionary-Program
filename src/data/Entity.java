package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.QuickLoad;
import static helpers.Clock.Delta;

import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import helpers.config;

public class Entity {
	private int width, height, health, healthRegen, attackVal, defenseVal, intelligence, maxHunger, currentHunger, hungerDepeletionRate, evasion;
	private float x, y;
	private double origSpeed,ySpeed, xSpeed;
	private Texture texture;
	private boolean first;
	
	public Entity(Random i, Random j) {
		this.texture = QuickLoad("Entity");	
		this.width = config.getTilesize(); 
		this.height = config.getTilesize();
		this.x = i.nextInt(config.getWidth());
		this.y = j.nextInt(config.getHeight());
		this.xSpeed = 0;
		this.ySpeed = 0; 
		this.origSpeed = (int) i.nextGaussian() * 2 + 5; 
		this.attackVal = (int) i.nextGaussian();
		this.defenseVal = (int) j.nextGaussian();
		this.health = 100;
		this.healthRegen = 1;
		this.maxHunger = 100;
		this.currentHunger = 100;
		this.hungerDepeletionRate = 1;
		this.setEvasion(0);
		this.first = true;
	}
	
	//Method to draw the entity at coordinate x,y with it's set texture
	public void Draw() {
		DrawQuadTex(texture, x, y, width, height);
	}
	
	public void Update() {
		if(first) {
			first = false;
		} else {
			x += Delta() * xSpeed;
			y += Delta() * ySpeed;
		}
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

	public double getOrigSpeed() {
		return origSpeed;
	}

	public void setOrigSpeed(double origSpeed) {
		this.origSpeed = origSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealthRegen() {
		return healthRegen;
	}

	public void setHealthRegen(int healthRegen) {
		this.healthRegen = healthRegen;
	}

	public int getAttackVal() {
		return attackVal;
	}

	public void setAttackVal(int attackVal) {
		this.attackVal = attackVal;
	}

	public int getDefenseVal() {
		return defenseVal;
	}

	public void setDefenseVal(int defenseVal) {
		this.defenseVal = defenseVal;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getMaxHunger() {
		return maxHunger;
	}

	public void setMaxHunger(int maxHunger) {
		this.maxHunger = maxHunger;
	}

	public int hungerDepeletionRate() {
		return hungerDepeletionRate;
	}

	public void hungerDepeletionRate(int hungerDepeletionRate) {
		this.hungerDepeletionRate = hungerDepeletionRate; 
	}

	public int getCurrentHunger() {
		return currentHunger;
	}

	public void setCurrentHunger(int currentHunger) {
		this.currentHunger = currentHunger;
	}

	public int getEvasion() {
		return evasion;
	}

	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}
}
