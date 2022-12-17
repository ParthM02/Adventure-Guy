package adventureGuy;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//This class is used for mouse detection
public class MouseChecker implements MouseListener{
	//Local GamePanel object
	GamePanel panel;
	//Constructor for mouse checker
	public MouseChecker(GamePanel panel) {
		this.panel = panel;
	}
	//Checks if a mouse clicked on something
	@Override
	public void mouseClicked(MouseEvent me) {
		panel.mouseClicked(me);
		
	}
	//Checks if mouse is pressed
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//Checks if mouse is released
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//Checks if mouse is hovering over something
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//Checks if mouse exited target area
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
