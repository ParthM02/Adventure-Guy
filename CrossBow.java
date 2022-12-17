package adventureGuy;
	
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.imageio.ImageIO;
//Class for the cross bow which is used to shoot enemies
public class CrossBow {
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
	
	//Image of cross bow facing forward direction
	private Image crossbow1;
	//Image of cross bow facing backward direction
	private Image crossbow2;
	
	//Local GamePanel object
	GamePanel panel;
	
	//Boolean to check if the cross bow should be forwards or backwards
	boolean faceFront = true;
	
	//Constructor for Cross Bow object
	public CrossBow(int x, int y, int width, int height, GamePanel panel) {
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
			URL url = getClass().getResource("crossbow.png");
			crossbow1 = ImageIO.read(url);
			
			URL url2 = getClass().getResource("crossbowBack.png");
			crossbow2 = ImageIO.read(url2);

		}
		catch(Exception e)
		{
			System.out.println("Image not found");
         
		}
	}
	//Updates X position and hit box
	public int setX(int cameraX) {
		hitbox.x = x;
		
		return x;
	}
	//Updates Y position and hit box
	public int setY(int cameraX) {
		hitbox.y = y;
		
		return y;
	}
	//Draws the crossbow image
	public void draw(Graphics2D gtd) {
		if (faceFront == true) {
			gtd.drawImage(crossbow1, x, y, width, height, null);
		}else {
			gtd.drawImage(crossbow2, x, y, width, height, null);
		}
	}
	//Used to hit the enemy with the cross bow if close enough
	public void hit() {
		try{
			for (Enemy enemy: panel.enemies) {
				if (hitbox.intersects(enemy.hitbox)) {
					panel.enemies.remove(enemy);
					panel.addCoin(5);
				}
			}
		}catch(Exception e) {
			//System.out.println("Enemy arraylist is dumb. Fix it");
		}
	}
}
