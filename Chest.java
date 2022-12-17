package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.imageio.ImageIO;

//This is the class is used to make the chest work in the game.
public class Chest {
	//X-position
	int x;
	//Y-position
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
	
	//Image variable
	private Image chest;

	//Local GamePanel object
	GamePanel panel;
	
	//boolean to check if chest has been used already
	boolean used = false;
	
	//Constructor for Chest object
	public Chest(int x, int y, int width, int height, GamePanel panel) {
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
			URL url = getClass().getResource("chest.png");
			chest = ImageIO.read(url);

		}
		catch(Exception e)
		{
			System.out.println("Image not found");
         
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
	//Draws the chest
	public void draw(Graphics2D gtd) {
		gtd.drawImage(chest, x, y, width, height, null);
	}
	


	//Decides whether to give coins or weapons
	public void giveStuff(){
		if (used == false) {
			if(panel.invitory[0] == false) {
				reward();
			}else if (panel.invitory[1] == false) {
				reward();
			}else {
				giveCoins();
			}
		}
		
	}
	//Rewards 5 coins to the player
	public void giveCoins() {
		if(used == false) {
			panel.addCoin(5);
			used = true;
		}
		
			
	}
	//Main reward algorithm that decides exactly what to give to the player in a randomized fashion
	public void reward() {
		int random = (int)(Math.random() * 3 + 1);
		if (random == 1) {
			if (used == false) {
				if(panel.invitory[0] == true) {
					reward();
				}else {
					panel.invitory[0] = true;
					panel.listItems[0] = true;
					used = true;
				}
				
			}
		}else if (random == 2) {
			if(used == false) {
				if(panel.invitory[1] == true) {
					reward();
				}else {
					panel.invitory[1] = true;
					panel.listItems[1] = true;
					used = true;
				}
				
			}
		}else {
			if(panel.invitory[0] == false && panel.invitory[1] == false) {
				reward();
			}else if(used == false) {
				giveCoins();
				used = true;
			}
		}
	}
}
