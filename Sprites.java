import java.awt.*;

public class Sprites {
	int id,x,y,width,height;
	Color colour = Color.RED; //IT'S COLOUR NOT COLOR, bloody yanks
	
	public Sprites(int id, int x, int y, int width, int height, Color colour) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.colour = colour;
	}
   
	public void paint(Graphics g) {
		g.setColor(colour);
		g.fillOval(x, y, width, height); 
	}
}