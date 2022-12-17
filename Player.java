package adventureGuy;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.imageio.ImageIO;

//This is the class for the player object
public class Player {
	
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
	
	//Horizontal speed
	double xSpeed;
	//Vertical speed
	double ySpeed;
	
	//Hit box for collision detection
	Rectangle hitbox;
	
	//Boolean for if left key pressed
	boolean keyLeft;
	//Boolean for if right key pressed
	boolean keyRight;
	//Boolean for if up key pressed
	boolean keyUp;
	//Boolean for if down key is pressed
	boolean keyDown;
	
	//Boolean for whether or not to reset
	boolean doReset;
	
	//Image of character facing forwards
	private Image guy;
	//Image of character facing backwards
	private Image guyBack;
	
	//Player Constructor
	public Player(int x, int y, GamePanel panel) {
		this.panel = panel;
		this.x = x;
		this.y = y;
		
		width = 50;
		height = 100;
		hitbox = new Rectangle(x, y, width, height);
		
		try
		{
			URL url = getClass().getResource("guy.png");
			guy = ImageIO.read(url);
			URL url2 = getClass().getResource("guyBack.png");
			guyBack = ImageIO.read(url2);
			
		}
		catch(Exception e)
		{
			//feel free to do something here
         
		}
		
	}
	//Used for updating the X and Y position as well as collision detection with ground.
	public void set() {
		if(keyLeft && keyRight || !keyLeft && !keyRight) {
			xSpeed *= .8;
		}else if(keyLeft && !keyRight) {
			xSpeed --;
		}else if(!keyLeft && keyRight) {
			xSpeed ++;
		}
		
		if(xSpeed > 0 && xSpeed < .75) {
			xSpeed =0;
		}
		if(xSpeed < 0 && xSpeed > -.75) {
			xSpeed = 0;
		}
		if(xSpeed > 9) {
			xSpeed = 9;
		}
		if(xSpeed < -9) {
			xSpeed = -9;
		}
		
		if(panel.takeMoveIn == true) {
			if(keyUp) {
				//check if touching ground
				hitbox.y ++;
				for (Wall wall: panel.walls) {
					if(wall.hitbox.intersects(hitbox)) {
						ySpeed = -11;
					}
				}
				hitbox.y --;

			}
			
			ySpeed += 0.5;
		}
		
		
		//horizontal collision
		hitbox.x += xSpeed;
		for (Wall wall: panel.walls) {
			if (hitbox.intersects(wall.hitbox)) {
				hitbox.x -= xSpeed;
				while (!wall.hitbox.intersects(hitbox)) {
					hitbox.x += Math.signum(xSpeed);
				}
					hitbox.x -= Math.signum(xSpeed);
					panel.cameraX += x- hitbox.x;
					xSpeed = 0;
					hitbox.x = x;
					//x = hitbox.x;
				
			}
		}
		//vertical collision
		hitbox.y += ySpeed;
		for (Wall wall: panel.walls) {
			if (hitbox.intersects(wall.hitbox)) {
				hitbox.y -= ySpeed;
				while (!wall.hitbox.intersects(hitbox)) {
					hitbox.y += Math.signum(ySpeed);
				}
					hitbox.y -= Math.signum(ySpeed);
					panel.cameraY += y - hitbox.y;
					ySpeed = 0;
					hitbox.y = y;
					//y = hitbox.y;
				
			}
		}
		
		panel.cameraX-=xSpeed;
		//y += ySpeed;
		panel.cameraY-=ySpeed;
		
		hitbox.x = x;
		hitbox.y = y;
		
		//Death Code for if player falls off map
		if(panel.level == 1) {
			if(panel.cameraY < -1200) {
				panel.reset();
			}
		}else if(panel.level == 2) {
			if(panel.cameraY < -1600) {
				panel.reset2();
				panel.score = 0;
			}
		}
			
		
	}
	
	//returns X value
	public int getX() {
		return x;
	}
	//Used to collect coins by the Player
	public void checkCoin() {
		try{
			for (Coin coin: panel.coins) {
				if (hitbox.intersects(coin.hitbox)) {
					panel.addCoin(1);
					panel.coins.remove(coin);
					
				}
			}
		}catch(Exception e) {
			//System.out.println("Coin array list modification is dumb. Fix it");
		}
			
	}
	//Used to open chests by the Player
	public void openIt() {
		try{
			for (Chest chest: panel.chests) {
				if (hitbox.intersects(chest.hitbox)) {
					chest.giveStuff();
					
				}
			}
		}catch(Exception e) {
			//System.out.println("Chest array list is dumb. Fix it");
		}
			
	}
	
	//Draws the player
	public void draw(Graphics2D gtd) {
		if(panel.front == true) {
			gtd.drawImage(guy, x, y, width, height, null);
		}else {
			gtd.drawImage(guyBack, x, y, width, height, null);
		}
		
	}
	//Checks if player dies from enemy collision
	public void checkDeath() {
		try{
			for (Enemy enemy: panel.enemies) {
				if (hitbox.intersects(enemy.hitbox)) {
					//doReset = true;
					if(panel.level == 1) {
						panel.reset();
					} else if(panel.level == 2) {
						panel.reset2();
						panel.score = 0;
					}
					
					break;
				}
			}
		}catch(Exception e) {
			//System.out.println("Enemy array list is dumb. Fix it");
		}
		
	}
	//Checks if player beats level 1
	public void beat1() {
		if (hitbox.intersects(panel.door.hitbox)) {
			panel.startLevel2();
		}
		
	}
	//Checks if player beats level 2
	public void checkFin() {
		if (hitbox.intersects(panel.flag.hitbox)) {
			if(panel.leaderboard.used == false) {
				panel.gameEnd();
			}
			
		}
		
	}
}
