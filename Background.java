package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Rectangle;
import java.net.URL;
import javax.imageio.ImageIO;
//This is used for the background image in the main menu
public class Background {
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
	
	//Image for the background
	private Image background;
	
	//Local GamePanel object
	GamePanel panel;
	
	//Constructor for background image
	public Background(int x, int y, int width, int height, GamePanel panel) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;

		
		this.panel = panel;
		
		
		try
		{
			URL url = getClass().getResource("home2.png");
			background = ImageIO.read(url);


		}
		catch(Exception e)
		{
			System.out.println("Image not found");
         
		}
	}
	

	//Draws Image
	public void draw(Graphics2D gtd) {
		gtd.drawImage(background, x, y, width, height, null);
	}
	
}
