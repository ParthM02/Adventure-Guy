package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.imageio.ImageIO;
//Class for the arrows that are shot from the cross bow
public class Arrow {
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
	
	//Arrow facing forwards
	private Image arrow1;
	//Arrow facing backwards
	private Image arrow2;
	
	//Local GamePanel object
	GamePanel panel;
	
	//Boolean to see if the arrow needs to face the front or back
	boolean faceFront;
	
	//Speed of arrow travel
	int speed = 8;
	
	//Constructor for arrow object
	public Arrow(int x, int y, int width, int height, GamePanel panel, boolean direction) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;
		
		hitbox = new Rectangle (x, y, width, height);
		
		this.panel = panel;
		
		faceFront = direction;
		
		
		try
		{
			URL url = getClass().getResource("arrow.png");
			arrow1 = ImageIO.read(url);
			
			URL url2 = getClass().getResource("arrowBack.png");
			arrow2 = ImageIO.read(url2);

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
	//Draws the arrow
	public void draw(Graphics2D gtd) {
		if (faceFront == true) {
			gtd.drawImage(arrow1, x, y, width, height, null);
		}else {
			gtd.drawImage(arrow2, x, y, width, height, null);
		}
	}
	//Moves the arrow depending on direction
	public void move() {
		if (faceFront == true) {
			x = x + speed;
			hitbox.x = x;
		}else if (faceFront == false){
			x = x - speed;
			hitbox.x = x;
		}
	}
	//Checks if the arrow hits and kills an enemy
	public void hit() {
		try{
			for (Enemy enemy: panel.enemies) {
				if (hitbox.intersects(enemy.hitbox)) {
					panel.enemies.remove(enemy);
					
					panel.addCoin(5);
					try {
						panel.shots.remove(this);
					}catch(Exception e) {
						//System.out.println("Arrow itself is dumb. Fix it");
					}
				}
			}
		}catch(Exception e) {
			//System.out.println("Arrow array list is dumb. Fix it");
		}
	}
}
