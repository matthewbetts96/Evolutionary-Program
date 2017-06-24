import java.util.*;
import java.io.*;
/*
Stats class will eventually be used to gather stats about the current generation of entities
Currently not in use.
*/
public class stats {
	public static void trackStats(Entity obj){
		int attack = obj.getAttack();
		int defense = obj.getDefense();
		int speed = obj.getSpeed();
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt",true)))) {
			writer.write(attack + "," + defense + "," + speed);
			writer.write("\n");
		} 
		catch (IOException ex) {
			// Handle me pls
		}  
	}
}