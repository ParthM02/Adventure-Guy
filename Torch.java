package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Graphics;
import java.awt.Rectangle;
//import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;
//Class for torches in level 2
public class Torch {
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
	
	//hit box
	Rectangle hitbox;
	
	//Torch image
	private Image torchImage;
	
	//Constructor for torch objects
	public Torch(int x, int y, int width, int height) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;

		hitbox = new Rectangle (x, y, width, height);
		
		try
		{
			URL url1 = getClass().getResource("torch.png");
			torchImage = ImageIO.read(url1);

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
	//Draws the torches
	public void draw(Graphics2D gtd) {
		gtd.drawImage(torchImage, x, y, width, height, null);
		
	}
	
}
