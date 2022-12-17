package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Rectangle;
import java.net.URL;
import javax.imageio.ImageIO;
//Class for the background trophy image in the end of the game
public class Trophy {
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
	
	//Trophy image
	private Image trophy;
	
	//Local GamePanel object
	GamePanel panel;
	
	//Constructor for trophy object
	public Trophy(int x, int y, int width, int height, GamePanel panel) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;

		this.panel = panel;
		
		
		try
		{
			URL url = getClass().getResource("trophy.png");
			trophy = ImageIO.read(url);


		}
		catch(Exception e)
		{
			System.out.println("Image not found");
         
		}
	}
	//Draws the trophy image
	public void draw(Graphics2D gtd) {
		gtd.drawImage(trophy, x, y, width, height, null);
	}

}
