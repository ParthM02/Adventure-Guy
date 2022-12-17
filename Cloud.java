package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Graphics;
import java.awt.Rectangle;
//import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;
//Class for the clouds drawn in level 1
public class Cloud {
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
	
	//hit box for future implementation of bouncy clouds. Not going be added in final project due to time
	Rectangle hitbox;
	
	//Cloud image
	private Image cloudImage;

	//Constructor for cloud object
	public Cloud(int x, int y, int width, int height) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;

		hitbox = new Rectangle (x, y, width, height);
		
		try
		{
			URL url1 = getClass().getResource("cloud.png");
			cloudImage = ImageIO.read(url1);

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
	//Draws the clouds
	public void draw(Graphics2D gtd) {
		gtd.drawImage(cloudImage, x, y, width, height, null);
		
	}
	
}
