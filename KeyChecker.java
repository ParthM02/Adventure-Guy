package adventureGuy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//Class used to see if a key is pressed
public class KeyChecker extends KeyAdapter{
	//Local GamePanel object
	GamePanel panel;
	
	//Constructor for key checker
	public KeyChecker(GamePanel panel) {
		this.panel = panel;
	}
	//Looks to see if a key is pressed
	@Override
	public void keyPressed(KeyEvent e) {
		panel.keyPressed(e);
		
	}
	//Looks to see if a key is released
	@Override
	public void keyReleased(KeyEvent e) {
		panel.keyReleased(e);
	}
}

