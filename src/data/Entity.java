package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Clock.Delta;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import helpers.Artist;
import helpers.Config;

public class Entity {
	private int width, height, attackVal, defenseVal, intelligence, 
	maxHunger, currentHunger, hungerDepeletionRate, baseSpeed, age, 
	ticksSinceLastChild, ticksSinceLastInteraction;
	
	private float xPosition, yPosition;
	private Texture texture;
	private double ySpeed, xSpeed;
	private String facingDirection;
	private EntityType species;
	private boolean first, isDead, isMale;
	
	//Constructor for making a new enitity at a random location
	public Entity(EntityType species) {
		Random rnd = new Random();
		//Position on the map
		this.xPosition = rnd.nextInt(Config.getWidth());
		this.yPosition = rnd.nextInt(Config.getHeight());
		
		//Speed of the creature
		this.xSpeed = 1;
		this.ySpeed = 1; 
		this.baseSpeed = rnd.nextInt(4);
		
		//Size of the creature 
		this.width = Config.getSize(); 
		this.height = Config.getSize();
		
		//Species type as defined by arg 0
		this.species = species;
		this.maxHunger = species.getMaxHunger();
		this.texture = Artist.getPrey();
		
		//Misc stats/values
		this.attackVal = rnd.nextInt(10);
		this.defenseVal = rnd.nextInt(10);
		this.intelligence = 1;//rnd.nextInt(10) + 5;
		this.currentHunger = species.getMaxHunger();
		this.hungerDepeletionRate = 1;
		this.age = 5400;
		this.ticksSinceLastChild = 0;
		this.ticksSinceLastInteraction = 0;
		this.isDead = false;
		this.first = true;
		this.facingDirection = "southeast";//set to southeast as that is what x=1,y=1 movement gives us 
		
		//50/50 split for male/female
		int sex = rnd.nextInt(2);
		if(sex == 1) {
			this.isMale = true;
		} else if(sex == 0){
			this.isMale = false;
		} 
	}
	
	public Entity(ArrayList<Integer> childStats) {
		Random rnd = new Random();
		//Position on the map
		this.xPosition = childStats.get(0);
		this.yPosition = childStats.get(1);
		
		//Speed of the creature
		this.xSpeed = 1;
		this.ySpeed = 1; 
		this.baseSpeed = rnd.nextInt(4);
		
		//Size of the creature 
		this.width = Config.getSize(); 
		this.height = Config.getSize();
		
		//Species type as defined by arg 0
		this.species = EntityType.Prey;
		this.maxHunger = species.getMaxHunger();
		this.texture = Artist.getPrey();
		
		//Misc stats/values
		this.attackVal = childStats.get(3);
		this.defenseVal = childStats.get(4);
		this.intelligence = 1;//childStats.get(5);
		if(this.intelligence <= 0) {
			if(this.intelligence == 0) {
				this.intelligence = 1;
			} else {
				this.intelligence = this.intelligence*-1;
			}
		}
		
		this.currentHunger = 20;
		this.hungerDepeletionRate = 1;
		this.age = 0;
		this.ticksSinceLastChild = 0;
		this.ticksSinceLastInteraction = 0;
		this.isDead = false;
		this.first = true;
		this.facingDirection = "southeast";//set to southeast as that is what x=1,y=1 movement gives us 
		
		//50/50 split for male/female
		int sex = rnd.nextInt(2);
		if(sex == 1) {
			this.isMale = true;
		} else if(sex == 0){
			this.isMale = false;
		} 
	}
	
	//Method to draw the entity at coordinate x,y with it's set texture
	public void Draw() {
		DrawQuadTex(texture, xPosition, yPosition, width, height);
	}
			
	//Updates the Entities position relative to the last time the screen was updated
	public void Update() {
		if(first) {
			first = false;
		} else {
			xPosition += Delta() * xSpeed;
			yPosition += Delta() * ySpeed;
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

	public int getCurrentHunger() {
		return currentHunger;
	}

	public void setCurrentHunger(int currentHunger) {
		this.currentHunger = currentHunger;
	}

	public int getHungerDepeletionRate() {
		return hungerDepeletionRate;
	}

	public void setHungerDepeletionRate(int hungerDepeletionRate) {
		this.hungerDepeletionRate = hungerDepeletionRate;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getTicksSinceLastChild() {
		return ticksSinceLastChild;
	}

	public void setTicksSinceLastChild(int ticksSinceLastChild) {
		this.ticksSinceLastChild = ticksSinceLastChild;
	}

	public float getxPosition() {
		return xPosition;
	}

	public void setxPosition(float xPosition) {
		this.xPosition = xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public void setyPosition(float yPosition) {
		this.yPosition = yPosition;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public String getFacingDirection() {
		return facingDirection;
	}

	public void setFacingDirection(String facingDirection) {
		this.facingDirection = facingDirection;
	}

	public EntityType getSpecies() {
		return species;
	}

	public void setSpecies(EntityType species) {
		this.species = species;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
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

	public boolean isMale() {
		return isMale;
	}

	public int getTicksSinceLastInteraction() {
		return ticksSinceLastInteraction;
	}

	public void setTicksSinceLastInteraction(int ticksSinceLastInteraction) {
		this.ticksSinceLastInteraction = ticksSinceLastInteraction;
	}
	
	
}
