package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Graphics;
import java.awt.Rectangle;
//import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;
//This is the class for the flag object at the end of level 2.
public class Flag {
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
	
	//Flag image
	private Image flag;
	
	//Constructor for the flag
	public Flag(int x, int y, int width, int height) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;
		
		hitbox = new Rectangle (x, y, width, height);
		
		try
		{
			URL url = getClass().getResource("flag.png");
			flag = ImageIO.read(url);
			
		}
		catch(Exception e)
		{
			//feel free to do something here
         
		}
	}
	
	//Updates X position
	public int setX(int cameraX) {
		x = startX + cameraX;
		hitbox.x = x;
		
		return x;
	}
	//Updates Y position
	public int setY(int cameraX) {
		y = startY + cameraX;
		hitbox.y = y;
		
		return y;
	}
	//Draws the flag
	public void draw(Graphics2D gtd) {
		gtd.drawImage(flag, x, y, width, height, null);
		
	}
}
