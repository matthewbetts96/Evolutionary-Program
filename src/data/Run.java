package data;

import static data.Startup.createSession;
import static helpers.Artist.preLoadTextures;

import java.io.IOException;

public class Run {

	public static void main(String[] args) throws IOException {
		new Run();
	}
	
	public Run() throws IOException {
		//Create the window and set openGL settings
		createSession();
		
		//Load textures to memory 
		preLoadTextures();
		
		//Begin game loop
		GameCycle loop = new GameCycle();
		loop.start();
	
	}
}