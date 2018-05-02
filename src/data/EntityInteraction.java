package data;

import java.util.ArrayList;
import java.util.Random;

public class EntityInteraction {
	
	
	public static void interact(TileGrid grid, int i, int j) {
		if(grid.GetTile(i, j).getCreaturesOnTile().size() == 2) {
			Entity e1 = grid.GetTile(i, j).getCreaturesOnTile().get(0);
			Entity e2 = grid.GetTile(i, j).getCreaturesOnTile().get(1);
			twoEntityInteraction(grid, i, j, e1, e2);
			twoEntityInteraction(grid, i, j, e2, e1);
		} else {
			int numberOnTile = grid.GetTile(i, j).getCreaturesOnTile().size();
			
			for(int counter = 0; counter < numberOnTile; counter++) {
				int thisEntity = 0;
				Entity focus = grid.GetTile(i, j).getCreaturesOnTile().get(thisEntity);
				
				//Where we store what is the furthest away 
				int closestEntNumber = 0;
				double closestEntDistance = 100000;
				
				//if it is not itself then enter loop
				if(thisEntity != counter) {
					Entity possibleTarget = grid.GetTile(i, j).getCreaturesOnTile().get(counter);
					double distance = getEuclidean(focus,possibleTarget);
					
					if(distance > closestEntDistance) {
						closestEntNumber = counter;
						closestEntDistance = distance;
					}
				}
				
				//After looping through all possible combinations of this entity, we now know which one is the closest 
				twoEntityInteraction(grid, i, j, focus, grid.GetTile(i, j).getCreaturesOnTile().get(closestEntNumber));
			}
		}
	}
	
	private static double getEuclidean(Entity e1, Entity e2) {
		float e1x = e1.getxPosition();
		float e1y = e1.getyPosition();
		float e2x = e2.getxPosition();
		float e2y = e2.getyPosition();
		
		float x = e1x - e2x;
		float y = e1y - e2y;
		
		float distance = (float) Math.sqrt((Math.pow(x,2)) + (Math.pow(y,2)));
		
		return distance;
	}

	private static void twoEntityInteraction(TileGrid grid, int i, int j, Entity e1, Entity e2) {
		//Ie true if they one is male and the other female
		if(e1.isMale() != e2.isMale()) {
			//next we do a check to see if both are eligible 
			if(e1.getTicksSinceLastChild() >= 180 && e2.getTicksSinceLastChild() >= 180) {
				//The last check is to see if they have the requirements 
				if(e1.getCurrentHunger() >= 20 && e2.getCurrentHunger() >= 20) {
					//This last check is to exclude 'children', we consider a child to be less than 5400 ticks (15 years)
					if(e1.getAge() >= 5400 && e2.getAge() >= 5400) {
						//If we get to this stage, then we can create a new offspring 
						//that has influences from both the mother and father
						ArrayList<Entity> creatureList = Spawn.getEntityList();
						ArrayList<Integer> crossBreedOutcome = crossBreed(e1, e2);
						creatureList.add(new Entity(crossBreedOutcome));
						Spawn.setEntityList(creatureList);
						getGaussian(e1.getAttackVal(), e2.getAttackVal());
						
						//While males don't have a value for TicksSinceLastChild, we reset both to 0 
						//as we won't know which one is male or female
						e1.setTicksSinceLastChild(0);
						e2.setTicksSinceLastChild(0);
						
						//remove some food as energy spent creating the offspring
						e1.setCurrentHunger(e1.getCurrentHunger() - 10);
						e2.setCurrentHunger(e2.getCurrentHunger() - 10);
						
						System.out.println("A child was born!");
					}
				}
			}
		} else if(e1.isMale() && e2.isMale()){
			//If both are male, they will fight over the 'territory' & 'possible mates'
			//We'll just assume that females won't
			//We're also going to exclude 'children' too
			if(e1.getAge() >= 5400 && e2.getAge() >= 5400) {
				//The very final check is to stop entities fighting to the death as soon as they meet
				if(e1.getTicksSinceLastInteraction() > 30 || e2.getTicksSinceLastInteraction() > 30) {
					creatureAttack(e1, e2);
					//reset interaction timer
					e1.setTicksSinceLastInteraction(0);
					e2.setTicksSinceLastInteraction(0);
				}
			}
		}
	}
	
	public static ArrayList<Integer> crossBreed(Entity male, Entity female) {
		
		//Mix male and female stats and return the stats of the offspring
		
		ArrayList<Integer> childStats = new ArrayList<Integer>();
		
		int childSpeed = getGaussian(male.getBaseSpeed(), female.getBaseSpeed());
		int childAttack = getGaussian(male.getAttackVal(), female.getAttackVal());
		int childDefense = getGaussian(male.getDefenseVal(), female.getDefenseVal());
		int childIntelligence = getGaussian(male.getIntelligence(), female.getIntelligence());
				
		childStats.add((int)male.getxPosition() + 1); //the coords for both male and
		childStats.add((int)male.getyPosition() + 1); //female will be the same 
		childStats.add(childSpeed); 		
		childStats.add(childAttack);		
		childStats.add(childDefense);		
		childStats.add(childIntelligence);
		return childStats;
	}
	
	
	public static int getGaussian(int male, int female) {
		int high = 0;
		int low = 0;
		Random x = new Random();
		
		if(male > female) {
			high = male;
			low = female;
		} else {
			high = female;
			low = male;
		}
		
		int mean = (int)(high - (high - low));
		int stdDev = (int)((high - (high - low))/4);
		if(stdDev == 0) {
			stdDev = 1;
		}
		return (int)x.nextGaussian() + mean * stdDev;
	}
	
	//while we'll call them attacker and defender, it doesn't really matter who is who
	//Both creatures will attack once 
	private static void creatureAttack(Entity att, Entity def) {
		//Getting all relevant variables
		int e1Att = att.getAttackVal();
		int e2Def = def.getDefenseVal();

		//As we don't want to deal with negatives, set them to 0
		if(e1Att > 0) {
			e1Att = 0;
		} 
		if(e2Def > 0) {
			e2Def = 0;
		}
			
		int attributeDiff = e1Att - e2Def;
		int percentageToKill = killChance(attributeDiff);
			
		int rand = new Random().nextInt(100);
			
		//if %to kill is higher, then attacker kills defender
		if(percentageToKill >= rand) {
			def.setDead(true);
			System.out.println(def + " died in battle.");
		}
	}
	
	/*	Attack vs Defense	
	(Attack - Defense)  Kill Chance %
		0                	 1
		1                    2
		2               	 4
		3                    8
		4                  	12
		5                  	16
		6                  	21
		7               	27
		8                  	35
		9                  	42
		10                	50
		11					58
		12					65
		13					72
		14					78
		15					83
		16					88
		17					92
		18					95
		19					97
		20					99
		21+					100
	*/
	private static int killChance(int attDefDifference) {
		int toReturn = 0;
		if(attDefDifference > 22) {
			attDefDifference = 22;
		}
		
		switch(attDefDifference) {
		case 0:
			toReturn = 1;
			break;
		
		case 1:
			toReturn = 2;
			break;
		
		case 2:
			toReturn = 4;
			break;
		
		case 3:
			toReturn = 8;
			break;
		
		case 4:
			toReturn = 12;
			break;
		
		case 5:
			toReturn = 16;
			break;
			
		case 6:
			toReturn = 21;
			break;
		
		case 7:
			toReturn = 27;
			break;
		
		case 8:
			toReturn = 35;
			break;
		
		case 9:
			toReturn = 42;
			break;
		
		case 10:
			toReturn = 50;
			break;
		
		case 11:
			toReturn = 58;
			break;
		
		case 12:
			toReturn = 65;
			break;
			
		case 14:
			toReturn = 72;
			break;
			
		case 15:
			toReturn = 78;
			break;
			
		case 16:
			toReturn = 83;
			break;
	
		case 17:
			toReturn = 88;
			break;
		
		case 18:
			toReturn = 92;
			break;
			
		case 19:
			toReturn = 95;
			break;
			
		case 20:
			toReturn = 97;
			break;
		
		case 21:
			toReturn = 99;
			break;
			
		case 22:
			toReturn = 100;
			break;	
		}
		return toReturn;
	}
}
