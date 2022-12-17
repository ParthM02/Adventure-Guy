package adventureGuy;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Graphics;
import java.awt.Rectangle;
//import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;
//Class for the walls that the players move and jump on
public class Wall {
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
	
	//Image for grass wall
	private Image imageGrass;
	//Image for dirt wall
	private Image imageDirt;
	//Image for wood wall
	private Image imageWood;
	
	//Tile type for that specific wall
	int tileType;
	//Constructor for wall objects
	public Wall(int x, int y, int width, int height, int type) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;
		
		tileType = type;
		
		hitbox = new Rectangle (x, y, width, height);
		
		try
		{
			URL url1 = getClass().getResource("grassTile2.png");
			imageGrass = ImageIO.read(url1);
			
			URL url2 = getClass().getResource("dirtTile.png");
			imageDirt = ImageIO.read(url2);
			
			URL url3 = getClass().getResource("woodTile.png");
			imageWood = ImageIO.read(url3);
		}
		catch(Exception e)
		{
			//feel free to do something here
         
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
	//Draws the wall tiles
	public void draw(Graphics2D gtd) {

		if(tileType == 1) {
			gtd.drawImage(imageGrass, x, y, width, height, null);
		}
		if(tileType == 2) {
			gtd.drawImage(imageDirt, x, y, width, height, null);
		}
		if(tileType == 3) {
			gtd.drawImage(imageWood, x, y, width, height, null);
		}
		
	}
}