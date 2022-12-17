package adventureGuy;

import java.awt.Dimension;
import java.awt.Toolkit;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//This is the main class that has the main method of the program.
public class AdventureGuy {
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		
		frame.setSize(700,700);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
		
		frame.setResizable(false);
		frame.setTitle("Adventure Guy");
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
}