package adventureGuy;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Rectangle;
import java.net.URL;

import javax.imageio.ImageIO;
//Class to show items in inventory 
public class ItemTile {
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
	
	//Tile pic for sword
	private Image swordTile;
	//Tile pic for cross bow
	private Image crossbowTile;
	
	//Tile type for displaying
	int tileType;
	
	//Constructor the Item Tiles
	public ItemTile(int x, int y, int width, int height, int type) {
		this.x = x;
		startX = x;
		this.y = y;
		startY = y;
		this.width = width;
		this.height = height;
		
		tileType = type;
		
		try
		{
			URL url1 = getClass().getResource("swordTile2.png");
			swordTile = ImageIO.read(url1);
			
			URL url2 = getClass().getResource("crossbowTile2.png");
			crossbowTile = ImageIO.read(url2);
		}
		catch(Exception e)
		{
			//feel free to do something here
         
		}
	}
	//Draws the Item Tiles 
	public void draw(Graphics2D gtd) {
		if(tileType == 1) {
			gtd.drawImage(swordTile, x, y, width, height, null);
		}
		if(tileType == 2) {
			gtd.drawImage(crossbowTile, x, y, width, height, null);
		}
		
	}
}
