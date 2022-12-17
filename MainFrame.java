package adventureGuy;

//This class contains the basic settings for the panel to properly show
public class MainFrame extends javax.swing.JFrame{
	//Local GamePanel object	
	GamePanel panel;
	//Constructor for the main panel
	public MainFrame() {
		panel = new GamePanel();
		panel.setLocation(0,0);
		panel.setSize(this.getSize());
		
		panel.setVisible(true);
		this.add(panel);
		
		addKeyListener(new KeyChecker(panel));
		addMouseListener(new MouseChecker(panel));
		
	}	
}
