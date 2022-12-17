package adventureGuy;

import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

//This is the class that handles all the data being written and read for the leaderboard
public class Leaderboard {
	
	//String for scores
	String scores;
	
	//First place score
	String firstplace = "No Data";
	//First place location in file
	int firstlocation;
	//First place name
	String firstplaceName = "No data";
	
	//Second place score
	String secondplace = "No Data";
	//Second Place location in file
	int secondlocation;
	//Second place name
	String secondplaceName = "No data";
	
	//Third place score
	String thirdplace = "No Data";
	//Third place location in file
	int thirdlocation;
	//Third place name
	String thirdplaceName = "No data";
	
	//Fourth place score
	String fourthplace = "No Data";
	//Fourth place location in file
	int fourthlocation;
	//Fourth place name
	String fourthplaceName = "No data";
	
	//Fifth place score
	String fifthplace = "No Data";
	//Fifth place location in file
	int fifthlocation;
	//Fifth place name
	String fifthplaceName = "No data";
	
	//Sixth place score
	String sixthplace = "No Data";
	//Sixth place location in file
	int sixthlocation;
	//Sixth place name
	String sixthplaceName = "No data";
	
	//Seventh place score
	String seventhplace = "No Data";
	//Seventh place location in file
	int seventhlocation;
	//Seventh place name
	String seventhplaceName = "No data";
	
	//Eighth place score
	String eighthplace = "No Data";
	//Eighth place location in file
	int eighthlocation;
	//Eighth place name
	String eighthplaceName = "No data";
	
	//Ninth place score
	String ninthplace = "No Data";
	//Ninth place location in file
	int ninthlocation;
	//Ninth place name
	String ninthplaceName = "No data";
	
	//Tenth place score
	String tenthplace = "No Data";
	//Tenth place location in file
	int tenthlocation;
	//Tenth place name
	String tenthplaceName = "No data";
	
	//Last attempt location in file
	int lastAttempt;
	//Last attempt score
	String lastWord = "No Data";
	//Last attempt name
	String lastAttemptName = "No data";
	
	//Check to see if a score has already been written for most recent attempt
	boolean used;
	
	//Basic constructor for leaderboard class
	public Leaderboard() {
		used = false;
	}
	
	//Writes the score to the file
	public void writeScore(int score) {
		try {
		      FileWriter myWriter = new FileWriter("leaderboard.txt", true);
		      myWriter.write(score + "\n");
		      used = true;
		      myWriter.close();
		      System.out.println("Successfully wrote score to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	//Writes the name to the file
	public void writeName(String name) {
		try {
		      FileWriter myWriter = new FileWriter("nameLeaderboard.txt", true);
		      myWriter.write(name + "\n");
		      //used = true;
		      myWriter.close();
		      System.out.println("Successfully wrote name to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	//Searches for first place score
	public void findFirst() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore)) {
		    		tempScore = data;
		    		firstplace = data;
		    		firstlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for first place name
	public void findFirstName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == firstlocation) {
		    		firstplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for second place score
	public void findSecond() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(firstplace)) {
		    		tempScore = data;
		    		secondplace = data;
		    		secondlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for second place name
	public void findSecondName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == secondlocation) {
		    		secondplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for third place score
	public void findThird() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(secondplace)) {
		    		tempScore = data;
		    		thirdplace = data;
		    		thirdlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for third place name
	public void findThirdName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == thirdlocation) {
		    		thirdplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for fourth place score
	public void findFourth() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(thirdplace)) {
		    		tempScore = data;
		    		fourthplace = data;
		    		fourthlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for fourth place name
	public void findFourthName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == fourthlocation) {
		    		fourthplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for fifth place score
	public void findFifth() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(fourthplace)) {
		    		tempScore = data;
		    		fifthplace = data;
		    		fifthlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for fifth place name
	public void findFifthName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == fifthlocation) {
		    		fifthplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for sixth place score
	public void findSixth() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(fifthplace)) {
		    		tempScore = data;
		    		sixthplace = data;
		    		sixthlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for sixth place name
	public void findSixthName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == sixthlocation) {
		    		sixthplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for seventh place score
	public void findSeventh() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(sixthplace)) {
		    		tempScore = data;
		    		seventhplace = data;
		    		seventhlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for seventh place name
	public void findSeventhName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == seventhlocation) {
		    		seventhplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for eighth place score
	public void findEighth() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(seventhplace)) {
		    		tempScore = data;
		    		eighthplace = data;
		    		eighthlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for eighth pace name
	public void findEighthName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == eighthlocation) {
		    		eighthplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for ninth place score
	public void findNinth() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(eighthplace)) {
		    		tempScore = data;
		    		ninthplace = data;
		    		ninthlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for ninth place name
	public void findNinthName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == ninthlocation) {
		    		ninthplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for tenth place score
	public void findTenth() {
		try {
			int currentPlace = 0; 
			String tempScore = "0";
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(Integer.parseInt(data) > Integer.parseInt(tempScore) && Integer.parseInt(data) < Integer.parseInt(ninthplace)) {
		    		tempScore = data;
		    		tenthplace = data;
		    		tenthlocation = currentPlace;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for tenth place name
	public void findTenthName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == tenthlocation) {
		    		tenthplaceName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for last attempt score
	public void findLastAttempt() {
		lastAttempt = 0;
		try {
			File myObj = new File("leaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	lastAttempt++; 
		    	String data = myReader.nextLine();
		    	if(myReader.hasNextLine()) {
		    		lastWord = data;
		    	}else {
		    		lastWord = data;
		    	}
		    }
		    myReader.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	//Searches for last attempt name
	public void findLastName() {
		try {
			int currentPlace = 0;
			File myObj = new File("nameLeaderboard.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	currentPlace++;
		    	String data = myReader.nextLine();
		    	if(currentPlace == lastAttempt) {
		    		lastAttemptName = data;
		    	}  
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	
	
}
