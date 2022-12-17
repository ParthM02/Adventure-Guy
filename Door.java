package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Graphics;
import java.awt.Rectangle;
//import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;
//Class for the door at the end of the first level
public class Door {
	//X position
	int x;
	//Y position
	int y;
	//Width
	int width;
	//Height
	int height;
	
	//Starting X position
	int startX;
	//Starting Y position
	int startY;
	
	//Hit box for collision detection
	Rectangle hitbox;
	
	//Image for the door
	private Image door;
	
	//Constructor for door object
	public Door(int x, int y, int width, int height) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;
		
		hitbox = new Rectangle (x, y, width, height);
		
		try
		{
			URL url = getClass().getResource("door.png");
			door = ImageIO.read(url);
			
		}
		catch(Exception e)
		{
			//feel free to do something here
         
		}
	}
	//Updates X position and hit box
	public int setX(int cameraX) {
		x = startX + cameraX;
		hitbox.x = x;
		
		return x;
	}
	//Updates Y position and hit box
	public int setY(int cameraX) {
		y = startY + cameraX;
		hitbox.y = y;
		
		return y;
	}
	//Draws the door
	public void draw(Graphics2D gtd) {
		gtd.drawImage(door, x, y, width, height, null);
		
	}
	
}
