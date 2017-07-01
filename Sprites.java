import java.awt.*;

public class Sprites {
	int x,y, width, height;
	Color colour = Color.RED; //IT'S COLOUR NOT COLOR, bloody yanks
	
	public Sprites(int x, int y, int width, int height, Color colour) {
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