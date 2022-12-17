package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.imageio.ImageIO;

//Class for the coins in the game
public class Coin {
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
	
	//hit box for collision detection
	Rectangle hitbox;
	
	//Coin Image
	private Image coin;

	//Constructor for the coin
	public Coin(int x, int y, int width, int height) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;
		
		hitbox = new Rectangle (x, y, width, height);
		
		
		try
		{
			URL url = getClass().getResource("coin.png");
			coin = ImageIO.read(url);

		}
		catch(Exception e)
		{
			System.out.println("Image not found");
         
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
	//Draws the coin
	public void draw(Graphics2D gtd) {
		gtd.drawImage(coin, x, y, width, height, null);
	}
}
