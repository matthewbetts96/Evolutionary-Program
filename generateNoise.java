import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.*;
import java.awt.*;
import java.io.*;

public class generateNoise {

    private static final int WIDTH = 200; 	//note to self: WIDTH x TILE_SIZE = T, where on line 79 and 80 i,j < T
    private static final int HEIGHT = 200; 	//CANVAS_HEIGHT and WIDTH must be T and T+1 respectively  
    private static final double FEATURE_SIZE = 24;
	private ArrayList<Integer> colours = new ArrayList<Integer>();

    public ArrayList generateNoise() {
        System.out.print("Generating Map...");
        OpenSimplexNoise noise = new OpenSimplexNoise();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        int rand = new Random().nextInt(100);
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, rand);
                int rgb = 0x010101 * (int)((value + 1) * 127.5);
                image.setRGB(x, y, rgb);
            }
        }
        try{
            ImageIO.write(image, "png", new File("noise.png"));
        } catch (IOException ex){
            System.out.println("IOException caught -generateNoise -generateNoise");
        }
        getColours();
		return colours;
    }

    public ArrayList getColours() {
        BufferedImage image = null;
        File file = new File("noise.png");
        try{
            image = ImageIO.read(file);
        } catch (IOException ex){
            System.out.println("IOException caught -generateNoise -getColours");
        }
        // Getting pixel color by position x and y 
        // Uses bit shifting to get individual pixel values as the pixels are returned as a packed int
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                int clr = image.getRGB(x,y); 
                int red   = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue  =  clr & 0x000000ff;
                if(red > 170){
                    red = 0;
                } else if(red > 110){
					red = 1;
				} else if(red > 65){
                    red = 2;
                } else {
                    red = 3;
                }
                colours.add(red); //using only the red value for now
            }
        }
		colours.add(0);
		colours.add(0);
		return colours;
    }
}



