//***********************************************************************************************
    // Written by: Tashfia Naharin Proma
	// COMP 248 Section U – Winter 2018
//***********************************************************************************************

//Date: 17th April 2018

/*This is the first class Definition file. It's name is Dice. It is basically used to create the
   all the 6 dices in this game and to determine what currentSide they land on randomly.
*/

import java.util.Random;

public class Dice {
	
//a)A Dice object has 2 attributes:	
	private String colour;
	private int currentSide;
	
//b)Default constructor which sets the colour to white and sets an initial currentSide using the rollDice() method (see below). 
	public Dice() {
		 colour= "white";
		 rollDice();	         
	}  
	
//c)A constructor that takes one input, a colour, and sets an initial currentSide using the rollDice() method (see below). 	
	public Dice(String colour) {
		this.colour=colour;
		this.rollDice();	
	}
	
//d)A get/set (mutator/accessor) method for colour and a get method for currentSide. 
	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getCurrentSide() {
		return currentSide;
	}
//e)A toString() method that returns the colour and current side of the dice as a String. 
	public String toString() {
		return colour + " dice: " + currentSide + " | " ;
	}
	
	Random randomSide = new Random();
	
//A rollDice () method To assign the currentSide with a random number from 1-6.
	public void rollDice() {
		this.currentSide = (1+randomSide.nextInt(6));
	}
	
}

