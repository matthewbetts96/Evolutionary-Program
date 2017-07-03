/* Each entity will have these values
	Attack 
	Defense 
	x,y coords (not implemented in 1st version)
	Movement speed (maybe do this over each type of terrain) (not needed until x,y positions are implemented)  
	Hunger (how much food they consume per time frame) (not needed until x,y positions are implemented)  
	Size (if size reaches a minimum they will die) (not needed until x,y positions are implemented)  
	Sex (m or f) (not needed until breeding is implemented) - maybe not even needed
	State (living or dead)
	https://www3.ntu.edu.sg/home/ehchua/programming/java/J4b_CustomGraphics.html
*/

import java.util.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.*;      
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class main extends JFrame {
	
	public static final int CANVAS_WIDTH  = 800;
	public static final int CANVAS_HEIGHT = 800;
	public static final int TILE_SIZE = 4; //This is both for pixel length and width of each tile
	public static final int rand = new Random().nextInt(1000);
	
	private DrawCanvas canvas;
	private Sprites sprite;
	private List noiseNumbers = new ArrayList<Integer>();
	public static LinkedList<Entity> entities = new LinkedList<Entity>();
	public static LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	public static void main (String[] args){
		//Generate the noise file 
		generateNoise noise = new generateNoise();
		noise.generateNoise(rand);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of entities to generate: ");
		int entNum = sc.nextInt();
		System.out.print("Enter number of generations to run: ");
		int genNum = sc.nextInt();
		
		//Create and store the new entities 
		for(int i = 0; i < entNum; i++){
			Entity creature = new Entity(i);
			entities.add(creature);
		}
		
		//Generates the map from noise.png in a seperate thread
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new main();
			}
		});
		getTiles object = new getTiles();
		tiles = object.getTileArray(TILE_SIZE);
		//we now have the tiles and all of the data within them
		//System.out.println(tiles.size());
		
	}
	
	public main(){		
		generateNoise noise = new generateNoise();
		noiseNumbers = noise.getColours();
		canvas = new DrawCanvas();    
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		setContentPane(canvas);
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		pack();              
		setTitle("Evolutionary Game");  
		setVisible(true);  
	}

	private class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);    
			setBackground(Color.WHITE);
			int x = 0;
			int y = 0;
			int pos = 0;
			int colourValue = 0;
			for(int i = 0; i < 800; i = i + TILE_SIZE){
				for(int j = 0; j < 800; j = j + TILE_SIZE){
					colourValue = (int)noiseNumbers.get(pos);
					if(colourValue == 3){
						g.setColor(Color.BLUE);
					} else if(colourValue == 2){
						g.setColor(Color.GREEN);
					} else if(colourValue == 1){
						g.setColor(Color.GRAY);
					} else {
						g.setColor(Color.BLACK);
					}
					g.fillRect(x,y,TILE_SIZE,TILE_SIZE);
					g.setColor(Color.BLACK);
					g.drawRect(x,y,TILE_SIZE,TILE_SIZE);
					pos = pos + 1;
					x = x + TILE_SIZE;
				}
				y = y + TILE_SIZE;
				x = 0;
			}
			//Should update the entities
			for(int i = 0; i < entities.size(); i++){
				sprite = new Sprites(entities.get(i).getID(),entities.get(i).getXpos(),entities.get(i).getYpos(),4,4, Color.RED);
				sprite.paint(g);
			}
		}
	}
}