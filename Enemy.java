package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.imageio.ImageIO;
//Class for the enemies who the player kills
public class Enemy {
	//Local GamePanel object
	GamePanel panel;
	
	//X position
	int x;
	//Y position
	int y;
	//Width
	int width;
	//Height
	int height;
	
	//Horizontal speed for when enemies move in future levels
	double xSpeed;
	//Vertical speed for when enemies move in future levels
	double ySpeed;
	
	//Starting X position
	double startX;
	//Starting Y position
	double startY;
	
	//Direction that it would have supposedly moved
	int dir = 1;
	
	//Hit box for collision detection
	Rectangle hitbox;
	
	//Image for enemy
	private Image badGuy;
	
	//Constructor for enemy object
	public Enemy(int x, int y, GamePanel panel, int type) {
		this.panel = panel;
		this.x = x;
		this.y = y;
		
		width = 50;
		height = 100;
		hitbox = new Rectangle(x, y, width, height);
		
		startX = x;
		startY = y;
		if(type == 1) {
			xSpeed = 0;
		}else if(type == 2) {
			xSpeed = 2;
		}
		
		try
		{
			URL url = getClass().getResource("enemy.png");
			badGuy = ImageIO.read(url);
			
		}
		catch(Exception e)
		{
			//feel free to do something here
         
		}
			
	}
	
	//Updates the X and Y positions and hit box
	public void set( int cameraX, int cameraY) {

		y = (int)startY + cameraY;
		hitbox.y = y;
		
		x = (int)startX + cameraX;
		hitbox.x = x;
		
	}
	
	
	//Draws the enemy
	public void draw(Graphics2D gtd) {
		gtd.drawImage(badGuy, x, y, width, height, null);
		
	}
}
