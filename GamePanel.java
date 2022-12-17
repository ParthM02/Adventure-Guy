package adventureGuy;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//The purpose of this class is to act as a sort of video output and 
//handles some methods that are not bound to a specific object or another class.

public class GamePanel extends javax.swing.JPanel implements ActionListener{
	
	//int count;
	
	//Player Object
	Player player;
	//ArrayList containing the floor and wall tiles
	ArrayList<Wall> walls = new ArrayList<>();
	//ArrayList containing enemies
	ArrayList<Enemy> enemies = new ArrayList<>();
	//ArrayList containing Coins
	ArrayList<Coin> coins = new ArrayList<>();
	//ArrayList containing chests
	ArrayList<Chest> chests = new ArrayList<>();
	//ArrayList containing arrows
	ArrayList<Arrow> shots = new ArrayList<>();
	//ArrayList that holds all the clouds
	ArrayList<Cloud> clouds = new ArrayList<>();
	//ArrayList that holds all the torches
	ArrayList<Torch> torches = new ArrayList<>();
	//Array used to check what's in my inventory 
	//I definitely spelled it wrong on purpose
	boolean[] invitory = new boolean[2];
	//Used to check which weapons to display on the inventory bar
	boolean[] listItems = new boolean[2];
	
	//Camera x-value
	int cameraX;
	//Camera y-value
	int cameraY;
	
	//Used for scheduled tasks 
	Timer gameTimer;
	
	//Main restart button
	Rectangle restartRect;
	//Main home button
	Rectangle homeRect;
	
	//Sword object
	Sword sword;
	//Crossbow object
	CrossBow crossbow;
	
	//sword tile object
	ItemTile swordInvitory;
	//crossbow tile object
	ItemTile crossbowInvitory;
	
	//door object
	Door door;
	//flag object
	Flag flag;
	
	//used to detect which direction the player is facing; front or back
	boolean front = true;
	
	//Score variable
	int score = 0;
	
	//Weapon selector
	int weapon;
	
	//level variable
	int level = 0;
	
	//custom color for the sky
	Color sky = new Color(94, 167, 235);
	
	//whether or not to show the door
	boolean showDoor;
	
	//whether or not to be able to take keyboard input
	boolean takeMoveIn;
	
	//Home Screen Stuff
	//Start button on home page
	Rectangle startButton;
	//leaderboard button on home page
	Rectangle leaderboardButton;
	//quit button on home page
	Rectangle quitButton;
	//help button on home page
	Rectangle helpButton;
	
	//background for home page
	Background background;
	
	//Leaderboard object
	Leaderboard leaderboard;
	//Leaderboard exit button
	Rectangle leaderboardExit;
	
	//help button
	Rectangle helpExit;
	
	//These are the numerous fonts used in the game
	Font buttonFont = new Font("Arial", Font.BOLD, 30);
	Font leaderFont = new Font("Arial", Font.BOLD, 16);
	Font endFont = new Font("Arial", Font.BOLD, 14);
	Font bigFont = new Font("Arial", Font.BOLD, 60);
	Font bigTitle = new Font("Arial", Font.BOLD, 40);
	Font helpFont= new Font("Arial", Font.BOLD, 20);
	
	//End game stuff
	//Big trophy image for end background
	Trophy trophy1;
	//boolean to show end screen
	boolean showEnd = false;
	//boolean to check if name was given
	boolean gaveName = false;
	//restart button
	Rectangle restartB2;
	//leaderboard button
	Rectangle leaderboardB2;
	//home button
	Rectangle homeB2;
	//boolean to check if used
	Boolean usedSubmit = false;
	
	//Constructor for the GamePanel
	public GamePanel() {
		restartRect = new Rectangle(550, 25, 50, 50);
		homeRect = new Rectangle(625, 25, 50, 50);
		
		startButton = new Rectangle(200, 300, 300, 50);
		leaderboardButton = new Rectangle(200, 375, 300, 50);
		helpButton = new Rectangle(200, 450, 300, 50);
		quitButton = new Rectangle(200, 525, 300, 50);
		
		background = new Background(0, 0, 700, 700, this);
		
		restartB2 = new Rectangle(300, 340, 100, 50);
		leaderboardB2 = new Rectangle(355, 280, 100, 50);
		homeB2 = new Rectangle(245, 280, 100, 50);
		
		leaderboardExit = new Rectangle(200, 525, 300, 50);
		helpExit = new Rectangle(200, 450, 300, 50);
		
		player = new Player(400, 300, this);
		
		sword = new Sword(player.x + 35, player.y + 60, 50, 50, this);
		crossbow = new CrossBow(player.x + 35, player.y + 95, 35, 29, this);
		
		swordInvitory = new ItemTile(250, 40, 70, 70, 1);
		crossbowInvitory = new ItemTile(350, 40, 70, 70, 2);
		
		door = new Door(4950, 462, 100, 161);
		flag = new Flag(7490, 532, 108, 168);
		
		trophy1 = new Trophy(20, 0, 660, 682, this);
		
		weapon = 0;
		
		leaderboard = new Leaderboard();
		
		deleteEverything();
		
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				
				try {
					if(level != 0 && level != -1 && level != -2 && level != -3) {
						setEverything();
					}
				}catch(Exception e) {
					
				}
				
				
				repaint();
				
			}
			
		}, 0, 15);
		
		
	}
	//Respawn for level 1
	public void reset() {
		
		cameraX = 150;
		cameraY = 300;
		
		player.x = 100;
		player.y = 350;
		player.xSpeed = 0;
		player.ySpeed = 0;
		
		
		
		walls.clear();
		makeWalls();
		
		
		enemies.clear();
		makeEnemies();
		
		coins.clear();
		makeCoins();
		
		chests.clear();
		makeChests();
		
		score = 0;
		level = 1;
		
		for (int i = 0; i < invitory.length; i++) {
			invitory[i] = false;
		}
		for (int i = 0; i < listItems.length; i++) {
			listItems[i] = false;
		}
		
		weapon = 0;
		
		shots.clear();
		clouds.clear();
		addClouds();
		torches.clear();
		
		showDoor = true;
		
		takeMoveIn = true;
		
		showEnd = false;
		
		gaveName = false;
		leaderboard.used = false;
	}
	//respawn for level 2
	public void reset2() {
		player.x = 150;
		player.y = 350;
		player.xSpeed = 0;
		player.ySpeed = 0;
		
		cameraX = -100;
		cameraY = 300;
		
		walls.clear();
		makeWalls2();
		
		torches.clear();
		makeTorches();
		
		enemies.clear();
		makeEnemies2();
		
		coins.clear();
		makeCoins2();
		
		chests.clear();
		makeChests2();
		
		level = 2;
		
		for (int i = 0; i < invitory.length; i++) {
			invitory[i] = false;
		}
		for (int i = 0; i < listItems.length; i++) {
			listItems[i] = false;
		}
		
		weapon = 0;
		
		shots.clear();
		clouds.clear();
		
		showDoor = false;
		
		takeMoveIn = true;
		
		gaveName = false;
		leaderboard.used = false;
	}
	
	//clears everything to display home screen
	public void deleteEverything() {
		player.x = 150;
		player.y = 350;
		player.xSpeed = 0;
		player.ySpeed = 0;
		
		cameraX = 150;
		cameraY = 300;
		
		walls.clear();
		enemies.clear();
		coins.clear();
		chests.clear();
		clouds.clear();
		torches.clear();
		for (int i = 0; i < invitory.length; i++) {
			invitory[i] = false;
		}
		for (int i = 0; i < listItems.length; i++) {
			listItems[i] = false;
		}
		shots.clear();
		
		weapon = 0;
		
		showDoor = false;
		
		takeMoveIn = false;
		level = 0;
		
		leaderboard.used = false;
		gaveName = false;
		
	}
	//Tells the leaderboard to fetch data from the files
	public void showLeaderboard() {
		level = -1;
		try {
			leaderboard.findFirst();
			leaderboard.findFirstName();
			
			leaderboard.findSecond();
			leaderboard.findSecondName();
			
			leaderboard.findThird();
			leaderboard.findThirdName();
			
			leaderboard.findFourth();
			leaderboard.findFourthName();
			
			leaderboard.findFifth();
			leaderboard.findFifthName();
			
			leaderboard.findSixth();
			leaderboard.findSixthName();
			
			leaderboard.findSeventh();
			leaderboard.findSeventhName();
			
			leaderboard.findEighth();
			leaderboard.findEighthName();
			
			leaderboard.findNinth();
			leaderboard.findNinthName();
			
			leaderboard.findTenth();
			leaderboard.findTenthName();
			
		}catch(Exception e) {
			
		}
		leaderboard.findLastAttempt();
		leaderboard.findLastName();
		
		
	}
	
	//at y <= 400, player gets stuck to wall unless they jump, may be useful as like a sticky wall.
	
	//Builds the floor and walls for level 1
	public void makeWalls() {
		for (int i = -150; i < 600; i+=50) {
			walls.add(new Wall(i, 600, 50, 50,1));
			
		}
		
		walls.add(new Wall(-200, 600, 50, 50,2));
		walls.add(new Wall(-200, 550, 50, 50,2));
		walls.add(new Wall(-200, 500, 50, 50,2));
		walls.add(new Wall(-200, 450, 50, 50,1));
		
		
		walls.add(new Wall(600, 550, 50, 50,1));
		walls.add(new Wall(650,500, 50, 50,1));
		walls.add(new Wall(700,500, 50, 50,1));
		walls.add(new Wall(750,500, 50, 50,1));
		walls.add(new Wall(800,500, 50, 50,1));
		walls.add(new Wall(850,500, 50, 50,1));
		
		walls.add(new Wall(1250,500, 50, 50,1));
		walls.add(new Wall(1250,550, 50, 50,2));
		walls.add(new Wall(1250,600, 50, 50,2));
		
		walls.add(new Wall(1500,500, 50, 50,1));
		walls.add(new Wall(1500,550, 50, 50,2));
		walls.add(new Wall(1500,600, 50, 50,2));
		
		walls.add(new Wall(1750,500, 50, 50,1));
		walls.add(new Wall(1750,550, 50, 50,2));
		walls.add(new Wall(1750,600, 50, 50,2));
		
		for (int i = 2000; i <= 2350; i+=50) {
			walls.add(new Wall(i, 600, 50, 50,1));
		}
		
		walls.add(new Wall(2400,550, 50, 50,1));
		walls.add(new Wall(2450,500, 50, 50,1));
		walls.add(new Wall(2500,450, 50, 50,1));
		walls.add(new Wall(2550,500, 50, 50,1));
		walls.add(new Wall(2600,550, 50, 50,1));
		
		for (int i = 2650; i <= 3000; i+=50) {
			walls.add(new Wall(i, 600, 50, 50,1));
		}
		
		walls.add(new Wall(3150,500, 50, 50,1));
		walls.add(new Wall(3400,500, 50, 50,1));
		walls.add(new Wall(3600,500, 50, 50,1));
		
		//location where underworld starts
		for (int i = 3750; i <= 4200; i+=50) {
			walls.add(new Wall(i, 600, 50, 50,1));
		}
		
		walls.add(new Wall(4250,650, 50, 50,1));
		walls.add(new Wall(4300,700, 50, 50,1));
		walls.add(new Wall(4350,750, 50, 50,1));
		walls.add(new Wall(4400,800, 50, 50,1));
		
		walls.add(new Wall(4450,750, 50, 50,1));
		walls.add(new Wall(4500,700, 50, 50,1));
		walls.add(new Wall(4550,650, 50, 50,1));
		
		walls.add(new Wall(4600,600, 50, 50,1));
		walls.add(new Wall(4650,600, 50, 50,1));
		walls.add(new Wall(4700,600, 50, 50,1));
		
		//add coin on top of this
		walls.add(new Wall(4750,700, 50, 50,1));
		
		for (int i = 4800; i <= 5000; i+=50) {
			walls.add(new Wall(i, 600, 50, 50,1));
		}

		
		//underworld   
		walls.add(new Wall(3150,1000, 50, 50,1));
		
		walls.add(new Wall(3100,1000, 50, 50,1));
		walls.add(new Wall(3050,1000, 50, 50,1));
		
		walls.add(new Wall(2800,1000, 50, 50,1));
		
		walls.add(new Wall(3200,1000, 50, 50,1));
		
		walls.add(new Wall(3300,900, 50, 50,1));
		walls.add(new Wall(3300,950, 50, 50,2));
		walls.add(new Wall(3300,1000, 50, 50,2));
		
		walls.add(new Wall(3400,1000, 50, 50,1));
		
		walls.add(new Wall(3450,900, 50, 50,1));
		walls.add(new Wall(3450,950, 50, 50,2));
		walls.add(new Wall(3450,1000, 50, 50,2));
		
		//Going back to surface
		walls.add(new Wall(3800,900, 50, 50,1));
		walls.add(new Wall(3700,800, 50, 50,1));
		walls.add(new Wall(3600,700, 50, 50,1));
		
		
		//still under
		
		for (int i = 3700; i <= 3800; i+=50) {
			walls.add(new Wall(i, 1000, 50, 50,1));
		}
		
		for (int i = 1000; i >= 650; i-=50) {
			walls.add(new Wall(3850, i, 50, 50,2));
		}
		
	}
	
	//Builds the floor and walls for level 2
	public void makeWalls2() {
		for (int i = -150; i < 600; i+=50) {
			walls.add(new Wall(i, 600, 50, 50, 3));
		}
		
		walls.add(new Wall(-200, 600, 50, 50,3));
		walls.add(new Wall(-200, 550, 50, 50,3));
		walls.add(new Wall(-200, 500, 50, 50,3));
		walls.add(new Wall(-200, 450, 50, 50,3));
		
		walls.add(new Wall(600, 650, 50, 50, 3));
		walls.add(new Wall(700, 750, 50, 50, 3));
		walls.add(new Wall(800, 850, 50, 50, 3));
		walls.add(new Wall(850, 900, 50, 50, 3));
		
		for (int i = 950; i <= 1250; i+=50) {
			walls.add(new Wall(i, 1000, 50, 50, 3));
		}
		
		walls.add(new Wall(1300, 950, 50, 50, 3));
		walls.add(new Wall(1350, 900, 50, 50, 3));
		walls.add(new Wall(1400, 850, 50, 50, 3));
		walls.add(new Wall(1450, 800, 50, 50, 3));
		walls.add(new Wall(1500, 750, 50, 50, 3));
		
		//over-world
		walls.add(new Wall(1750, 650, 50, 50, 3));
		walls.add(new Wall(2000, 650, 50, 50, 3));
		walls.add(new Wall(2250, 650, 50, 50, 3));
		walls.add(new Wall(2500, 650, 50, 50, 3));
		
		//under-world
		for (int i = 1550; i <= 2600; i+=50) {
			walls.add(new Wall(i, 1300, 50, 50, 3));
		}
		
		//stairway up
		walls.add(new Wall(2800, 1250, 50, 50, 3));
		walls.add(new Wall(2850, 1150, 50, 50, 3));
		walls.add(new Wall(2750, 1050, 50, 50, 3));
		
		//main world
		walls.add(new Wall(1750, 1000, 50, 50, 3));
		walls.add(new Wall(1800, 1000, 50, 50, 3));
		walls.add(new Wall(1850, 1000, 50, 50, 3));
		walls.add(new Wall(1900, 1000, 50, 50, 3));
		
		walls.add(new Wall(2000, 900, 50, 50, 3));
		walls.add(new Wall(2000, 950, 50, 50, 3));
		walls.add(new Wall(2000, 1000, 50, 50, 3));
		
		walls.add(new Wall(2150, 900, 50, 50, 3));
		walls.add(new Wall(2150, 950, 50, 50, 3));
		walls.add(new Wall(2150, 1000, 50, 50, 3));
		
		walls.add(new Wall(2300, 900, 50, 50, 3));
		walls.add(new Wall(2300, 950, 50, 50, 3));
		walls.add(new Wall(2300, 1000, 50, 50, 3));
		
		for (int i = 2450; i <= 2700; i+=50) {
			walls.add(new Wall(i, 1000, 50, 50, 3));
		}
		
		for (int i = 2900; i <= 3200; i+=50) {
			walls.add(new Wall(i, 950, 50, 50, 3));
		}
		
		walls.add(new Wall(3350, 850, 50, 50, 3));
		walls.add(new Wall(3500, 950, 50, 50, 3));
		walls.add(new Wall(3650, 850, 50, 50, 3));
		walls.add(new Wall(3800, 950, 50, 50, 3));
		walls.add(new Wall(3950, 850, 50, 50, 3));
		
		for (int i = 4100; i <= 4700; i+=50) {
			walls.add(new Wall(i, 950, 50, 50, 3));
		}
		
		walls.add(new Wall(4800, 900, 50, 50, 3));
		walls.add(new Wall(4850, 900, 50, 50, 3));
		walls.add(new Wall(4900, 900, 50, 50, 3));
		
		walls.add(new Wall(5000, 850, 50, 50, 3));
		walls.add(new Wall(5050, 850, 50, 50, 3));
		walls.add(new Wall(5100, 850, 50, 50, 3));
		
		walls.add(new Wall(5200, 800, 50, 50, 3));
		walls.add(new Wall(5250, 800, 50, 50, 3));
		walls.add(new Wall(5300, 800, 50, 50, 3));
		
		walls.add(new Wall(5400, 850, 50, 50, 3));
		walls.add(new Wall(5450, 850, 50, 50, 3));
		walls.add(new Wall(5500, 850, 50, 50, 3));
		
		//another under-world
		walls.add(new Wall(5350, 1100, 50, 50, 3));
		walls.add(new Wall(5400, 1100, 50, 50, 3));
		walls.add(new Wall(5450, 1100, 50, 50, 3));
		walls.add(new Wall(5500, 1100, 50, 50, 3));
		
		//another pair of stairs
		walls.add(new Wall(5650, 1050, 50, 50, 3));
		walls.add(new Wall(5650, 850, 50, 50, 3));
		walls.add(new Wall(5800, 950, 50, 50, 3));
		
		for (int i = 5950; i <= 6250; i+=50) {
			walls.add(new Wall(i, 1100, 50, 50, 3));
		}
		
		walls.add(new Wall(6400, 1000, 50, 50, 3));
		walls.add(new Wall(6550, 900, 50, 50, 3));
		walls.add(new Wall(6700, 800, 50, 50, 3));
		walls.add(new Wall(6800, 800, 50, 50, 3));
		
		for (int i = 6850; i <= 7500; i+=50) {
			walls.add(new Wall(i, 700, 50, 50, 3));
		}
		
	}
	
	//generates enemies for level 1
	public void makeEnemies() {
		enemies.add(new Enemy(800, 400, this, 1));
		enemies.add(new Enemy(2150, 500, this, 1));
		enemies.add(new Enemy(2400, 450, this, 1));
		enemies.add(new Enemy(3000, 500, this, 1));
		enemies.add(new Enemy(3050, 900, this, 1));
		enemies.add(new Enemy(4050, 500, this, 1));
		enemies.add(new Enemy(4700, 500, this, 1));
	}
	//generates enemies for level 2
	public void makeEnemies2() {
		//under-world
		enemies.add(new Enemy(1900, 1200, this, 1));
		enemies.add(new Enemy(1800, 1200, this, 1));
		enemies.add(new Enemy(2550, 1200, this, 1));
		
		//main world
		enemies.add(new Enemy(1900, 900, this, 1));
		enemies.add(new Enemy(2550, 900, this, 1));
		
		enemies.add(new Enemy(3150, 850, this, 1));
		
		enemies.add(new Enemy(4250, 850, this, 1));
		enemies.add(new Enemy(4600, 850, this, 1));
		
		enemies.add(new Enemy(5500, 750, this, 1));
		//The second under-world
		enemies.add(new Enemy(5500, 1000, this, 1));
		
		enemies.add(new Enemy(6250, 1000, this, 1));
		
		enemies.add(new Enemy(7000, 600, this, 1));
		enemies.add(new Enemy(7150, 600, this, 1));
	}
	//generates coins for level 1
	public void makeCoins() {
		coins.add(new Coin(4750, 625, 50, 50));
		coins.add(new Coin(1500, 425, 50, 50));
		coins.add(new Coin(2500, 375, 50, 50));
		coins.add(new Coin(3400, 425, 50, 50));
		coins.add(new Coin(3700, 925, 50, 50));
		coins.add(new Coin(3750, 925, 50, 50));
		coins.add(new Coin(550, 525, 50, 50));
	}
	//generates coins for level 2
	public void makeCoins2() {
		coins.add(new Coin(800, 775, 50, 50));
		coins.add(new Coin(1450, 725, 50, 50));
		
		//over-world coins
		coins.add(new Coin(1750, 575, 50, 50));
		coins.add(new Coin(2500, 575, 50, 50));
		
		//under-world
		coins.add(new Coin(2000, 1225, 50, 50));
		coins.add(new Coin(2050, 1225, 50, 50));
		coins.add(new Coin(2100, 1225, 50, 50));
		coins.add(new Coin(2150, 1225, 50, 50));
		coins.add(new Coin(2200, 1225, 50, 50));
		coins.add(new Coin(2250, 1225, 50, 50));
		coins.add(new Coin(2300, 1225, 50, 50));
		coins.add(new Coin(2350, 1225, 50, 50));
		coins.add(new Coin(2400, 1225, 50, 50));
		
		//main world
		coins.add(new Coin(2150, 825, 50, 50));
		
		coins.add(new Coin(3000, 875, 50, 50));
		
		coins.add(new Coin(3500, 875, 50, 50));
		coins.add(new Coin(3800, 875, 50, 50));
		
		coins.add(new Coin(5050, 775, 50, 50));
		
		coins.add(new Coin(5800, 875, 50, 50));
		
		coins.add(new Coin(6000, 1025, 50, 50));
		coins.add(new Coin(6150, 1025, 50, 50));
		
		coins.add(new Coin(6800, 725, 50, 50));
		coins.add(new Coin(6900, 625, 50, 50));
	}
	//generates chests for level 1
	public void makeChests() {
		chests.add(new Chest( 2800, 950, 50, 50, this));
		chests.add(new Chest( -150, 550, 50, 50, this));
		chests.add(new Chest( 2650, 550, 50, 50, this));
		chests.add(new Chest( 4400, 750, 50, 50, this));
	}
	//generates chests for level 2
	public void makeChests2() {
		chests.add(new Chest( -150, 550, 50, 50, this));
		chests.add(new Chest( 1550, 1250, 50, 50, this));
		chests.add(new Chest( 2850, 1100, 50, 50, this));
		chests.add(new Chest( 4400, 900, 50, 50, this));
		chests.add(new Chest( 5350, 1050, 50, 50, this));
		chests.add(new Chest( 6550, 850, 50, 50, this));
	}
	
	//generates random clouds for level 1
	public void addClouds() {
		for(int i = 0; i < 7800; i+=200) {
			int randomX = (int)(Math.random() * 70 + 1);
			int randomY = (int)(Math.random() * 100 + 1);
			clouds.add(new Cloud(i + randomX, 100 + randomY, 145, 55));
		}
	}
	//generates the torches for level 2 (not randomized)
	public void makeTorches() {
		torches.add(new Torch(10 , 450 , 27, 69));
		torches.add(new Torch(210 , 450 , 27, 69));
		torches.add(new Torch(410 , 450 , 27, 69));
		torches.add(new Torch(610 , 450 , 27, 69));
		
		torches.add(new Torch(1010 , 850 , 27, 69));
		torches.add(new Torch(1260 , 850 , 27, 69));
		
		torches.add(new Torch(1510 , 600 , 27, 69));
		
		torches.add(new Torch(1760 , 450 , 27, 69));
		torches.add(new Torch(2010 , 450 , 27, 69));
		torches.add(new Torch(2260 , 450 , 27, 69));
		torches.add(new Torch(2510 , 450 , 27, 69));
		
		torches.add(new Torch(1610 , 1100 , 27, 69));
		torches.add(new Torch(1810 , 1100 , 27, 69));
		torches.add(new Torch(2010 , 1100 , 27, 69));
		torches.add(new Torch(2210 , 1100 , 27, 69));
		torches.add(new Torch(2410 , 1100 , 27, 69));
		torches.add(new Torch(2610 , 1100 , 27, 69));
		
		torches.add(new Torch(2860 , 1000 , 27, 69));
		
		torches.add(new Torch(1810 , 800 , 27, 69));
		torches.add(new Torch(2160 , 700 , 27, 69));
		torches.add(new Torch(2610 , 800 , 27, 69));
		
		torches.add(new Torch(3010 , 750 , 27, 69));
		torches.add(new Torch(3210 , 750 , 27, 69));
		
		torches.add(new Torch(3360 , 650 , 27, 69));
		torches.add(new Torch(3660 , 650 , 27, 69));
		torches.add(new Torch(3960 , 650 , 27, 69));
		
		torches.add(new Torch(4210 , 750 , 27, 69));
		torches.add(new Torch(4410 , 750 , 27, 69));
		torches.add(new Torch(4610 , 750 , 27, 69));
		
		torches.add(new Torch(4860 , 700 , 27, 69));
		torches.add(new Torch(5060 , 650 , 27, 69));
		torches.add(new Torch(5260 , 600 , 27, 69));
		torches.add(new Torch(5460 , 650 , 27, 69));
		
		torches.add(new Torch(5410 , 950 , 27, 69));
		
		torches.add(new Torch(5660 , 650 , 27, 69));
		
		torches.add(new Torch(6010 , 900 , 27, 69));
		torches.add(new Torch(6210 , 900 , 27, 69));
		
		torches.add(new Torch(6410 , 800 , 27, 69));
		torches.add(new Torch(6560 , 700 , 27, 69));
		torches.add(new Torch(6710 , 600 , 27, 69));
		
		torches.add(new Torch(6910 , 500 , 27, 69));
		torches.add(new Torch(7110 , 500 , 27, 69));
		torches.add(new Torch(7310 , 500 , 27, 69));
	}
	
	//This class displays everything that needs to be displayed,
	//whether it be text or objects
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D gtd = (Graphics2D) g;

		if(level == 0) {
			background.draw(gtd);
		}
		
		if(level == -2) {
			trophy1.draw(gtd);
		}
		
		for(Enemy enemy: enemies) {
			enemy.draw(gtd);
		}
		for(Wall wall: walls) {
			wall.draw(gtd);
		}
		
		
		for(Arrow arrow: shots) {
			arrow.draw(gtd);
		}
		
		for(Cloud cloud: clouds) {
			cloud.draw(gtd);
		}
		
		for(Torch torch: torches) {
			torch.draw(gtd);
		}
		
		gtd.setColor(Color.BLACK);
		
		if (level != 0 && level != -1 && level != -2 && level != -3) {
			gtd.drawRect(550, 25, 50, 50);
			gtd.drawRect(625, 25, 50, 50);
		}else if (level == 0) {
			gtd.drawRect(200, 300, 300, 50);
			gtd.drawRect(200, 375, 300, 50);
			gtd.drawRect(200, 450, 300, 50);
			gtd.drawRect(200, 525, 300, 50);
		}else if (level == -1){
			gtd.drawRect(100, 200, 500, 300);
			gtd.drawRect(200, 525, 300, 50);
		}else if (level == -2) {
			if(gaveName == true) {
				gtd.drawRect(245, 280, 100, 50);
				gtd.drawRect(355, 280, 100, 50);
				gtd.drawRect(300, 340, 100, 50);
			}
		}else if(level == -3) {
			gtd.drawRect(200, 450, 300, 50);
			gtd.drawRect(100,225,500,200);
		}
		
		gtd.setColor(Color.yellow);
		if(level != 0 && level != -1 && level != -2 && level != -3) {
			gtd.setColor(Color.white);
			gtd.fillRect(551, 26, 48, 48);
			gtd.fillRect(626, 26, 48, 48);
		}else if (level == 0) {
			gtd.setColor(Color.yellow);
			gtd.fillRect(201, 301, 298, 48);
			gtd.fillRect(201, 376, 298, 48);
			gtd.fillRect(201, 451, 298, 48);
			gtd.fillRect(201, 526, 298, 48);
		}else if(level == -1){
			gtd.setColor(Color.yellow);
			gtd.fillRect(101, 201, 498, 298);
			gtd.fillRect(201, 526, 298, 48);
		}else if(level == -2) {
			gtd.setColor(Color.white);
			if(gaveName == true) {
				gtd.fillRect(246, 281, 98, 48);
				gtd.fillRect(356, 281, 98, 48);
				gtd.fillRect(301, 341, 98, 48);
			}
		}else if(level == -3) {
			gtd.fillRect(201, 451, 298, 48);
			gtd.fillRect(101, 226, 498, 198);
		}
		
		gtd.setColor(Color.BLACK);
		gtd.setFont(buttonFont);
		if(level != 0 && level != -1 && level != -2 && level != -3) {
			gtd.drawString("R", 564, 60);
			gtd.drawString("H", 639, 60);
		}else if (level == 0){
			gtd.drawString("Start Game", 275, 335);
			gtd.drawString("Leaderboard", 260, 410);
			gtd.drawString("How to Play", 265, 485);
			gtd.drawString("Quit Game", 275, 560);
		}else if(level == -1){
			gtd.drawString("Leaderboard", 260, 235);
			gtd.drawString("Home Menu", 265, 560);
			gtd.setFont(leaderFont);
			gtd.drawString("First Place: " + leaderboard.firstplace, 120, 270);
			gtd.drawString("Second Place: " + leaderboard.secondplace, 120, 290);
			gtd.drawString("Third Place: " + leaderboard.thirdplace, 120, 310);
			gtd.drawString("Fourth Place: " + leaderboard.fourthplace, 120, 330);
			gtd.drawString("Fifth Place: " + leaderboard.fifthplace, 120, 350);
			gtd.drawString("Sixth Place: " + leaderboard.sixthplace, 120, 370);
			gtd.drawString("Seventh Place: " + leaderboard.seventhplace, 120, 390);
			gtd.drawString("Eighth Place: " + leaderboard.eighthplace, 120, 410);
			gtd.drawString("Ninth Place: " + leaderboard.ninthplace, 120, 430);
			gtd.drawString("Tenth Place: " + leaderboard.tenthplace, 120, 450);
			
			gtd.drawString("Player: " + leaderboard.firstplaceName, 320, 270);
			gtd.drawString("Player: " + leaderboard.secondplaceName, 320, 290);
			gtd.drawString("Player: " + leaderboard.thirdplaceName, 320, 310);
			gtd.drawString("Player: " + leaderboard.fourthplaceName, 320, 330);
			gtd.drawString("Player: " + leaderboard.fifthplaceName, 320, 350);
			gtd.drawString("Player: " + leaderboard.sixthplaceName, 320, 370);
			gtd.drawString("Player: " + leaderboard.seventhplaceName, 320, 390);
			gtd.drawString("Player: " + leaderboard.eighthplaceName, 320, 410);
			gtd.drawString("Player: " + leaderboard.ninthplaceName, 320, 430);
			gtd.drawString("Player: " + leaderboard.tenthplaceName, 320, 450);
			
			gtd.drawString("Last Attempt: " + leaderboard.lastWord + " by " + leaderboard.lastAttemptName, 120, 480);
		}else if (level == -2) {
			gtd.setFont(endFont);
			if(gaveName == true) {
				gtd.drawString("Home", 275, 310);
				gtd.drawString("Leaderboard", 363, 310);
				gtd.drawString("Restart", 325, 370);
			}else {
				gtd.drawString("Enter Name in the Pop-Up to be in the Leaderboard", 176, 135);
			}
			
			
			gtd.setFont(buttonFont);
			gtd.drawString("Congradulations!!!", 225, 100);
			gtd.setFont(bigTitle);
			gtd.drawString("Score", 295, 190);
			gtd.setFont(bigFont);
			gtd.drawString(score + "", 300, 250);
			
		}else if(level == -3) {
			gtd.drawString("Home", 310, 485);
			gtd.setFont(helpFont);
			gtd.drawString("How to Play", 290, 255);
			gtd.setFont(endFont);
			gtd.drawString("•Use Arrow Keys to move and jump", 120, 280);
			gtd.drawString("•Press Shift to open chests for weapons and coins", 120, 305);
			gtd.drawString("•Use Number Keys to select weapon in invitory", 120, 330);
			gtd.drawString("•Press Space to use weapon on enemies", 120, 355);
			gtd.drawString("•Get points by opening chests, collecting coins, and killing enemies", 120, 380);
			gtd.drawString("•Advance through levels by walking into doors and flag", 120, 405);
			
		}
		
		if(level != 0 && level != -1 && level != -2 && level != -3) {
			if(level == 1) {
				gtd.setColor(Color.BLACK);
			}else if(level == 2) {
				gtd.setColor(Color.WHITE);
			}
			gtd.drawString("LEVEL: " + level, 270, 30);
			gtd.drawString("Score: " + score, 25, 30);
		}
		
		if(coins.size() > 0) {
			player.checkCoin();
		}
		
		autoRemove();
		
		
		
		if(invitory[0] == true) {
			swordInvitory.draw(gtd);
		}
		if(invitory[1] == true) {
			crossbowInvitory.draw(gtd);
		}
		
		if(showDoor == true) {
			door.draw(gtd);
		}
		
		if(level != 0 && level != -1 && level != -2 && level != -3) {
			player.draw(gtd);
		}
		
		if (invitory[0] == true) {
			if(weapon == 1) {
				sword.draw(gtd);
			}
		}
		if (invitory[1] == true ) {
			if(weapon == 2) {
				crossbow.draw(gtd);
			}
		}
		
		for(Coin coin: coins) {
			coin.draw(gtd);
		}
		for(Chest chest: chests) {
			chest.draw(gtd);
		}
		
		if(level == 2) {
			flag.draw(gtd);
		}
		
		player.checkDeath();
		player.beat1();
		if(leaderboard.used == false && showEnd == false) {
			player.checkFin();
		}
		
		checkBackground();		
	}
	
	//Needed for game to run but basically useless at the same time
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	}
	
	//Used to check if a key is pressed
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (takeMoveIn == true) {
				player.keyLeft = true;
				front = false;
				sword.faceFront = false;
				crossbow.faceFront = false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(takeMoveIn == true) {
				player.keyUp = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.keyDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(takeMoveIn == true) {
				player.keyRight = true;
				front = true;
				sword.faceFront = true;
				crossbow.faceFront = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			openChest();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			attemptHit();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_1) {
			if (invitory[0] == true) {
				weapon = 1;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			if (invitory[1] == true) {
				weapon = 2;
			}
		}
		
	}
	
	//Used to check if a key is released
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.keyLeft = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.keyUp = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.keyDown = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.keyRight = false;
	}
	
	//Used to make the buttons work by detecting which location the mouse is pressed
	void mouseClicked(MouseEvent me) {
		if(level != 0 && level != -1 && level != -2 && level != -3) {
			if(restartRect.contains(new Point(me.getPoint().x, me.getPoint().y - 27))) {
				if(level == 0) {
					reset();
				}else if (level == 1 ) {
					reset();
				}else if(level == 2) {
					reset2();
					score = 0;
				}
			}
			
			if(homeRect.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				deleteEverything();
			}
		}else if (level == 0){
			if(startButton.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				reset();
			}
			if(leaderboardButton.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				showLeaderboard();
				
			}
			if(quitButton.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				System.exit(0);
			}
			if(helpButton.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				deleteEverything();
				level = -3;
			}
		}else if(level == -1){
			if(leaderboardExit.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				deleteEverything();
			}
		}else if(level == -2 && gaveName == true) {
			if(restartB2.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				reset();
			}
			if(leaderboardB2.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				showLeaderboard();
				
			}
			if(homeB2.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				deleteEverything();
				level = 0;
			}
		}else if(level == -3) {
			if(helpExit.contains(new Point(me.getPoint().x, me.getPoint().y -27))) {
				deleteEverything();
				level = 0;
			}
		}
	}
	
	//Adds to the score when called
	public void addCoin(int i) {
		score += i;
	}
	
	
	//Method that calls for the player to open chest
	public void openChest() {
		player.openIt();
	}
	
	//Called when the player wants to attack an enemy by either shooting an arrow or "swinging" the sword
	public void attemptHit() {
		if(weapon == 1) {
			sword.hit();
		}else if(weapon == 2) {
			if(front == true) {
				shots.add(new Arrow(crossbow.x + crossbow.width/2 - 15, crossbow.y + 8, 60, 14, this, front));
			}else {
				shots.add(new Arrow(crossbow.x + crossbow.width/2 - 35, crossbow.y + 8, 60, 14, this, front));
			}
		}
	}
	
	//Called to remove arrow after it reaches a certain distance to lessen memory strain
	public void autoRemove() {
		try{
			if(shots.size() > 0) {
				for(Arrow arrow: shots) {
					arrow.hit();
					arrow.move();
					if (arrow.x > player.x + 500 || arrow.x < player.x - 500) {
						shots.remove(arrow);
					}
				}
			}
		}catch(Exception e) {
			//System.out.println("Fix auto remover");
		}
			
	}
	//Starts level 2
	public void startLevel2() {
		reset2();
	}
	
	//Refreshes the coordinates and hit boxes of all the objects when called
	public void setEverything () {
		player.set();
		if (front == true) {
			sword.x = player.x + 40;
			crossbow.x = player.x + 40;
		} else {
			sword.x = player.x - 45;
			crossbow.x = player.x - 25;
		}
		sword.setX(cameraX);
		sword.setY(cameraY);
		crossbow.setX(cameraX);
		crossbow.setY(cameraY);
		
		for(Enemy enemy: enemies) {
			enemy.set(cameraX, cameraY);
		}
		
		for(Wall wall: walls) {
			wall.setX(cameraX);
			wall.setY(cameraY);
		}
		
		for(Coin coin: coins) {
			coin.setX(cameraX);
			coin.setY(cameraY);
		}
		for(Chest chest: chests) {
			chest.setX(cameraX);
			chest.setY(cameraY);
		}
		
		for(Cloud cloud: clouds) {
			cloud.setX(cameraX);
			cloud.setY(cameraY);
		}
		
		for(Torch torch: torches) {
			torch.setX(cameraX);
			torch.setY(cameraY);
		}
		
		door.setX(cameraX);
		door.setY(cameraY);
		
		flag.setX(cameraX);
		flag.setY(cameraY);
	}
	
	//Checks if a score hasn't already been saved and then sends the score to the leaderboard object
	public void saveScore() {
		if (leaderboard.used == false) {
			leaderboard.writeScore(score);
		}
		
	}
	
	//Resets everything for the game end screen
	public void gameEnd() {
		player.x = 100;
		player.y = 350;
		player.xSpeed = 0;
		player.ySpeed = 0;
		
		cameraX = 150;
		cameraY = 300;
		
		walls.clear();
		enemies.clear();
		coins.clear();
		chests.clear();
		clouds.clear();
		torches.clear();
		for (int i = 0; i < invitory.length; i++) {
			invitory[i] = false;
		}
		for (int i = 0; i < listItems.length; i++) {
			listItems[i] = false;
		}
		shots.clear();
		
		weapon = 0;
		
		showDoor = false;
		
		takeMoveIn = false;
		
		level = -2;
		
		showEnd = true;
		
		requestName();

		
	}
	//Changes background color based on level that player is on
	public void checkBackground() {
		if(level != 2) {
			this.setBackground(sky);
		}else if(level ==2) {
			this.setBackground(Color.BLACK);
		}
	}
	
	//Creates pop-up window for the player to fill out their name and then sends it to leaderboard object
	public void requestName() {
		final JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("Click Me To Enter Leaderboard Name!");
        parent.add(button);
        parent.pack();
        parent.setVisible(true);
        parent.setDefaultCloseOperation(EXIT_ON_CLOSE);

        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = JOptionPane.showInputDialog(parent, "What is your name?", null);
                parent.setVisible(false);
                leaderboard.writeName(name);
                gaveName = true;
                saveScore();
            }
        });
        
	}
	
	
}
