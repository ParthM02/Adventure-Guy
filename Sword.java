package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.imageio.ImageIO;
//Class for Sword object
public class Sword {
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
	
	//Sword facing front
	private Image sword1;
	//Sword facing back
	private Image sword2;
	
	//Local GamePanel object
	GamePanel panel;
	
	//Boolean to check if facing front
	boolean faceFront = true;
	
	//Constructor for Sword object
	public Sword(int x, int y, int width, int height, GamePanel panel) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;
		
		hitbox = new Rectangle (x, y, width, height);
		
		this.panel = panel;
		
		
		try
		{
			URL url = getClass().getResource("sword.png");
			sword1 = ImageIO.read(url);
			
			URL url2 = getClass().getResource("swordBack.png");
			sword2 = ImageIO.read(url2);

		}
		catch(Exception e)
		{
			System.out.println("Image not found");
         
		}
	}
	//Updates the X position and hit box
	public int setX(int cameraX) {
		hitbox.x = x;
		
		return x;
	}
	//Updates the Y position and hit box
	public int setY(int cameraX) {
		hitbox.y = y;
		
		return y;
	}
	//Draws the sword
	public void draw(Graphics2D gtd) {
		if (faceFront == true) {
			gtd.drawImage(sword1, x, y, width, height, null);
		}else {
			gtd.drawImage(sword2, x, y, width, height, null);
		}
	}
	//Method that kills the enemies
	public void hit() {
		try{
			for (Enemy enemy: panel.enemies) {
				if (hitbox.intersects(enemy.hitbox)) {
					panel.enemies.remove(enemy);
					panel.addCoin(5);
				}
			}
		}catch(Exception e) {
			//System.out.println("Enemy array list is dumb. Fix it");
		}
	}
}
