//***********************************************************************************************
    // Written by: Tashfia Naharin Proma
	// COMP 248 Section U – Winter 2018
//***********************************************************************************************

//Date: 17th April 2018

/*This is the Second class Definition file. It's name is Move. It is basically used  for the 
  players to move when their turns comes.
*/
public class Move {
	
//a)A Move object has 2 attributes:
	private char colour;
	private int number;
	
//b)Get/set (mutator/accessor) methods for colour and number. 
	public char getColour() {
		return colour;
	}

	public void setColour(char colour) {
		this.colour = colour;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
//c)A constructor that takes two inputs, a colour and a number and sets the attributes accordingly.
	public Move(char colour,int number) {
		this.colour=colour;
		this.number=number;	
	}
	
/*d)A static methods convertColourtoNum(char colour) method that takes a char and
	converts it to the index of the row for that colour (i.e. ‘R’ = 0, ‘Y’ = 1, G =’2’, ‘B’ = 3) and
	returns that index. This method will help you index the right array element during the
	game to cross it out. */
	public static int convertColourtoNum(char colour) {
		int index=0;

		if (colour =='R')
		index=0;
		else if (colour == 'Y')
		index=1;
		else if (colour == 'G')
		index=2;
		else if (colour== 'B')
		index=3;
		
		return index;
	}
	

}
