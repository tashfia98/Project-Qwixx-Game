//************************************************************************************************
    // Written by: Tashfia Naharin Proma
	// COMP 248 Section U – Winter 2018
//***********************************************************************************************

//Date: 17th April 2018

/*This is the Third class Definition file. It's name is Player. This is one of an essential class.
  It creates the gameboard, calculates the points, makes the players move on the board and update
  it etc.
*/
public class Player {

//a)A Player object has seven attributes: 
	private String name;                                
	private String [][] gameBoard = new String [4][11];
	
//these are to keep track of the last number crossed out in each colour row.
	private int lastRedNumber;
	private int lastYellowNumber;
	private int lastGreenNumber;
	private int lastBlueNumber;
	
//to keep track of negative points.
	private int negativePoints;
	
/*b)A default constructor which initializes all of the attributes, the game board should be
initialized using the initializeGameboard() method described below. */
	public Player() {
		name=null;
		initializeGameboard();
		lastRedNumber=2; 
		lastYellowNumber=2;
		lastGreenNumber=12;
		lastBlueNumber=12;
		negativePoints=0;
    }
	
//c)A constructor which takes one parameter, a String for the player name. 
	public Player(String name) {
		this.name=name;
		initializeGameboard();
		lastRedNumber=2; 
		lastYellowNumber=2;
		lastGreenNumber=12;
		lastBlueNumber=12;
		negativePoints=0;
	}

//d)Accessor/get methods for each of the attributes except the game board.
	public String getName() {
		return name;
	}

	public int getLastRedNumber() {
		return lastRedNumber;
    }
  
	public int getLastYellowNumber() {
		return lastYellowNumber;
    }

	public int getLastGreenNumber() {
		return lastGreenNumber;
    }

	public int getLastBlueNumber() {
		return lastBlueNumber;
    }

	public int getNegativePoints() {
		return negativePoints;
    }

//e)An initializeGameboard() which initializies each row of the gameboard.
	public void initializeGameboard() {
		
		 for (int row=0 ; row<gameBoard.length; row++)
		 {  
		 	  
		 	  for(int column=0;column<gameBoard[row].length;column++ )	   
		 	  { 
		 		   if( row==0|| row ==1)
		 		   {  
		 			       if (column==0) {gameBoard[row][column]="2";}
		 			  else if (column==1) {gameBoard[row][column]="3";}	   
		 			  else if (column==2) {gameBoard[row][column]="4";} 
		 			  else if (column==3) {gameBoard[row][column]="5";} 
		 			  else if (column==4) {gameBoard[row][column]="6";} 
		 			  else if (column==5) {gameBoard[row][column]="7";}
		 			  else if (column==6) {gameBoard[row][column]="8";} 
		 			  else if (column==7) {gameBoard[row][column]="9";}
		 			  else if (column==8) {gameBoard[row][column]="10";} 
		 			  else if (column==9) {gameBoard[row][column]="11";}  
		 			  else if (column==10) {gameBoard[row][column]="12";}     
		 		   }
		 		   
			 	   else if(row ==2 || row==3)   
			 	   {
			 		       if (column==0) {gameBoard[row][column]="12";}
		 			  else if (column==1) {gameBoard[row][column]="11";}	   
		 			  else if (column==2) {gameBoard[row][column]="10";} 
		 			  else if (column==3) {gameBoard[row][column]="9";} 
		 			  else if (column==4) {gameBoard[row][column]="8";} 
		 			  else if (column==5) {gameBoard[row][column]="7";}
		 			  else if (column==6) {gameBoard[row][column]="6";} 
		 			  else if (column==7) {gameBoard[row][column]="5";}
		 			  else if (column==8) {gameBoard[row][column]="4";} 
		 			  else if (column==9) {gameBoard[row][column]="3";}  
		 			  else if (column==10) {gameBoard[row][column]="2";}    
			 	   }
		      }
		 }
	  }

/*f)An addNegativePoints(int pts) method that takes a parameter pts and adds these to the 
 negativePoints of the player. */
	public void addNegativePoints(int pts) {
		 negativePoints=(negativePoints + pts);
    }	
	
/*g)A printGameBoard() method that prints out the player’s gameboard (including the name
of the player who’s board is being printed. */
	public void printGameBoard() {
		
	    System.out.println(name +"'s Gameboard: "); 
	    
		System.out.print("   Red:");
		for (int  a=0; a<11;a++)
	         System.out.printf("%3s",gameBoard[0][a]);
		System.out.println();
		System.out.print("Yellow:");
		for (int  a=0; a<11;a++)
	         System.out.printf("%3s",gameBoard[1][a]);
		System.out.println();
		System.out.print(" Green:"); 
		for (int  a=0; a<11;a++)
		    System.out.printf("%3s",gameBoard[2][a]);
		System.out.println();
		System.out.print("  Blue:"); 
		for (int  a=0; a<11;a++)
		    System.out.printf("%3s",gameBoard[3][a]);
		System.out.println();
	  }

/*h)A makeMove(Move m)method, that takes as input a move and crosses off the appropriate
colour/number combination on the player’s gameboard. Hint: you may wish to make use
of your convertColourtoNum(char colour) method.  */
	 public void makeMove(Move m) {
		int n = m.getNumber();
	    char c= m.getColour();
	    int a= m.convertColourtoNum(c); 
	       
	    for(int b=0; b<gameBoard[a].length;b++) {
	    
	        if(gameBoard[a][b].equals(String.valueOf(n)))
	        gameBoard[a][b] = "X";  
	        
	    }
	 } 
/*i)A getBoardTotalMethod() which calculates the total for the gameboard based on the
following (the top row is how many numbers are crossed off, the bottom how many
points). You should calculate the points per each colour based on how many numbers
were crossed off and subtract the player’s negative points to get the total.  */
	public int getBoardTotalMethod() 
	{   int[] row = new int[4];
		int pointsInRed=0;
		int  pointsInYellow=0;
		int pointsInGreen=0;
		int pointsInBlue=0;
		
		for(int i=0; i<=3; i++)
		{
			for(int j=0; j<11; j++)
			{   if(gameBoard[i][j] == "X")
			    row[i]++;	
			}		
		}
	
		for(int i=row[0]; i > 0; i--)
		pointsInRed = (pointsInRed+i);
		
		for(int i=row[1]; i > 0; i--)
		pointsInYellow = (pointsInYellow+i);
		
		for(int i=row[2]; i > 0; i--)
    	pointsInGreen = (pointsInGreen+i);
		
		for(int i=row[3]; i > 0; i--)
		pointsInBlue = (pointsInBlue+i);
		
		
		return ((pointsInRed + pointsInYellow + pointsInGreen + pointsInBlue) + this.getNegativePoints());
	}

//To set the values.
	public void setRedLast(int lastRedNumber) {
        this.lastRedNumber = lastRedNumber;
    }
	
	public void setYellowLast(int lastYellowNumber) {
        this.lastYellowNumber = lastYellowNumber;
    }
	
	public void setGreenLast(int lastGreenNumber) {
        this.lastGreenNumber = lastGreenNumber;
    }
	
	public void setBlueLast(int lastBlueNumber) {
        this.lastBlueNumber = lastBlueNumber;
    }
	
	
	

}
