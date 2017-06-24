import java.util.Random;

public class killCalc{
	public boolean killCalculator(Entity attacker, Entity defender){
		int attackVal = attacker.getAttack();
		int defenseVal = defender.getDefense();
		int aSpeed = attacker.getSpeed();
		int dSpeed = defender.getSpeed();
		
		boolean killed = false;
		int attackDiff = attackVal - defenseVal;
		int speedDiff = dSpeed - aSpeed;
		double killChance = 0;
		
		//Trim the extremes of the attack/speed values (ie the 0% and 100% chance outcomes)
		if(attackDiff < -1){
			attackDiff = -1;
		}
		if(attackDiff > 20){
			attackDiff = 99999;
		}
		if(speedDiff < -10){
			speedDiff = -99999;
		}
		if(speedDiff > 10){
			speedDiff = 99999;
		}
		
		switch (attackDiff) {
			case -1: killChance = 0; break;
			case 0: killChance = 1; break;
			case 1: killChance = 2; break;
            case 2: killChance = 4; break;
			case 3: killChance = 8; break;
			case 4: killChance = 12; break;
			case 5: killChance = 16; break;
            case 6: killChance = 21; break;
			case 7: killChance = 27; break;
			case 8: killChance = 35; break;
			case 9: killChance = 42; break;
			case 10: killChance = 50; break;
			case 11: killChance = 58; break;
			case 12: killChance = 65; break;
			case 13: killChance = 72; break;
			case 14: killChance = 78; break;
			case 15: killChance = 83; break;
			case 16: killChance = 88; break;
			case 17: killChance = 92; break;
			case 18: killChance = 95; break;
			case 19: killChance = 97; break;
			case 20: killChance = 99; break;
			case 99999: killChance = 100; break;
        }
		switch (speedDiff) {
			case -99999: killChance = (killChance * 2); break;
			case -10: killChance = (killChance * 1.99); break;
			case -9: killChance = (killChance * 1.98); break;
			case -8: killChance = (killChance * 1.92); break;
			case -7: killChance = (killChance * 1.84); break;
			case -6: killChance = (killChance * 1.63); break;
			case -5: killChance = (killChance * 1.52); break;
			case -4: killChance = (killChance * 1.48); break;
			case -3: killChance = (killChance * 1.28); break;
			case -2: killChance = (killChance * 1.17); break;
			case -1: killChance = (killChance * 1.08); break;
			case 0: killChance = (killChance * 1); break;
			case 1: killChance = (killChance * 0.92); break;
            case 2: killChance = (killChance * 0.83); break;
			case 3: killChance = (killChance * 0.72); break;
			case 4: killChance = (killChance * 0.58); break;
			case 5: killChance = (killChance * 0.42); break;
            case 6: killChance = (killChance * 0.27); break;
			case 7: killChance = (killChance * 0.16); break;
			case 8: killChance = (killChance * 0.08); break;
			case 9: killChance = (killChance * 0.02); break;
			case 10: killChance = (killChance * 0.01); break;
			case 99999: killChance = 0; break;
        }
		int rand = new Random().nextInt(100);
		if(killChance > rand){
			killed = true;
		}
		return killed;
	}
}