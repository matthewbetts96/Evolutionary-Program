import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.io.File;

public class generateNoise {

    private static final int WIDTH = 200; 	//note to self: WIDTH * TILE_SIZE = T, where in main on DrawCanvas 'for' loops i and j < T
    private static final int HEIGHT = 200; 	//CANVAS_HEIGHT and WIDTH in main must also be T
    private static final double FEATURE_SIZE = 24;
	private ArrayList<Integer> colours = new ArrayList<Integer>();

    public void generateNoise(int rand) {
		try{
			File file = new File("noise.png");
			if(file.delete()){
				System.out.println("Resetting " + file.getName());
			}else{
				System.out.println("Delete operation is failed.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		OpenSimplexNoise noise = new OpenSimplexNoise();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
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
			System.out.println("Map Generated!");
		} catch (IOException ex){
			System.out.println("IOException caught -generateNoise -generateNoise");
		}
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
                    red = 0; 	//Mountains
                } else if(red > 110){
					red = 1;	//Highlands
				} else if(red > 65){
                    red = 2;	//Grasslands
                } else {
                    red = 3;	//Water
                }
                colours.add(red); //using only the red value for now (doesn't actually matter which one you use, they are all the same)
            }
        }
		return colours;
    }
}



