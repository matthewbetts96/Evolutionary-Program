package data;


import static helpers.Artist.BeginSession;

import org.lwjgl.opengl.Display;

public class Boot {
	
	public Boot() {
		BeginSession();
		
		TileGrid grid = new TileGrid();
		while(!Display.isCloseRequested()) {
			
			grid.Draw();
			Display.update();
			Display.sync(24);
		}
	}
	
	public static void main(String[] args) {
		new Boot();
	}
}
