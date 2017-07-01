/* Each entity will have these values
	Name
	Attack 
	Defense 
	Movement speed (maybe do this over each type of terrain)
	Hunger (how much food they consume per time frame)
	Size (if size reaches a minimum they will die)
	Sex (m or f)
	State (living or dead)
*/
import java.util.Random;
import java.util.*;

public class Entity {
	private String name;
	private int x;
	private int y;
	private int attack;
	private int defense;
	private int speed;
	private int hunger;
	private int size;
	private boolean sex;

	Random rand = new Random();
	
	public Entity(){

	}

	public Entity(int id){
		name = Integer.toString(id);
		x = 0;
		y = 0;
		attack = rand.nextInt(30);
		defense = rand.nextInt(20);
		speed = rand.nextInt(10) + 1; //Minimum 1 speed
		hunger = 100;
		size = 50;
		sex = true; //true and false for m and f for now
	}

	public Entity(Object obj){
		this.name = name;
		this.x = x;
		this.y = y;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed; 
		this.hunger = hunger;
		this.size = size;
		this.sex = sex; 
	}

	public String getName(){ 
		return name;
	}

	public int getXpos(){
		return x;
	}

	public int getYpos(){
		return y;
	}

	public int getAttack(){
		return attack;
	}

	public int getDefense(){
		return defense;
	}

	public int getSpeed(){
		return speed;
	}

	public int getHunger(){
		return hunger;
	}

	public int getSize(){
		return size;
	}

	public boolean getSex(){
		return sex;
	}
}