/* Each entity will have these values
	Attack 
	Defense 
	x,y coords (not implemented in 1st version)
	Movement speed (maybe do this over each type of terrain) (not needed until x,y positions are implemented)  
	Hunger (how much food they consume per time frame) (not needed until x,y positions are implemented)  
	Size (if size reaches a minimum they will die) (not needed until x,y positions are implemented)  
	Sex (m or f) (not needed until breeding is implemented)  
	State (living or dead)
*/

import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class main {
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		LinkedList<Entity> entities = new LinkedList<Entity>();
		System.out.print("Enter number of entities to generate: ");
		int entNum = sc.nextInt();
		
		//Create and store the new entities 
		for(int i = 0; i < entNum; i++){
			Entity creature = new Entity(i);
			entities.add(creature);
		}
		
		test testing = new test();
		testing.test(entities.get(1));

		int numberKilled = 0;
		int totalKilled = 0;
		killCalc confrontation = new killCalc();
		//Number of generations
		for(int p = 0; p < 1; p++){
			//number of attacks per generation
			for(int q = 0; q < 1; q++){
				//% of entities attacking
				for(int r = 0; r < entities.size()/2; r++){
					int entity1 = new Random().nextInt(entities.size());
					int entity2 = new Random().nextInt(entities.size());
					
					//need to add in error checking to make sure that entity1 + 2 are not the same (highly unlikely to be a problem at larger pool numbers)
					
					int attackVal = entities.get(entity1).getAttack();
					int defenseVal = entities.get(entity2).getDefense();
					int attackSpeed = entities.get(entity1).getSpeed();
					int defSpeed = entities.get(entity2).getSpeed();
				
					boolean isKill = confrontation.killCalculator(attackVal,defenseVal,attackSpeed,defSpeed);
					
					if(isKill == true){
						numberKilled++;
						totalKilled++;
						entities.remove(entity2);
					}
				}
			}
			System.out.println("Number Killed: " + numberKilled);
			System.out.println("Total Killed: " + totalKilled);
			numberKilled = 0;
			int currentEntNum = entities.size();
			
			
			//Print out i random entities per generation
			for(int i = 0; i < 1; i++){
				int rand = new Random().nextInt(entities.size());
				System.out.println("___________________");
				System.out.println("List Size = " + entities.size());
				System.out.println("Name = " + entities.get(rand).getName());
				System.out.println("Attack: " + entities.get(rand).getAttack());
				System.out.println("Defense: " + entities.get(rand).getDefense());
				System.out.println("Speed: " + entities.get(rand).getSpeed());
			}
			
			//replenishes the pool of entities to the original size
			//not sure if needed though 
			for(int i = currentEntNum; i < entNum; i++){
				Entity creature = new Entity(i);
				entities.add(creature);
			}
			System.out.println("Generation finished.");
			System.out.println("_________________________________");
			
			//stores stats of each generation
			//Not quite there yet...
			/*stats tracker = new stats();
			for(int i = 0; i < currentEntNum; i++){
				int attack = entities.get(i).getAttack();
				int defense = entities.get(i).getDefense();
				int speed = entities.get(i).getSpeed();
				tracker.trackStats(attack,defense,speed,currentEntNum);
			}*/
		}
	}
}



/*
			System.out.println("___________________");
			System.out.println("Name = " + entities.get(entity1));
			System.out.println("Name = " + entities.get(entity1).getName());
			System.out.println("Attack: " + entities.get(entity1).getAttack());
			System.out.println("Defense: " + entities.get(entity1).getDefense());
			System.out.println("Speed: " + entities.get(entity1).getSpeed());
			System.out.println("___________________");
			System.out.println("Name = " + entities.get(entity2));
			System.out.println("Name = " + entities.get(entity2).getName());
			System.out.println("Attack: " + entities.get(entity2).getAttack());
			System.out.println("Defense: " + entities.get(entity2).getDefense());
			System.out.println("Speed: " + entities.get(entity2).getSpeed());
			System.out.println("___________________");*/

