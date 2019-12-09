//***********************************************************************************************
    // Written by: Tashfia Naharin Proma
	// COMP 248 Section U – Winter 2018
//***********************************************************************************************

//Date: 17th April 2018

/*This is java file contains Two files. A class definition file Called Qwixx. and Driver File
 (main method). The methods in the Qwixx class basically combines everything and make the game
 work. The Driver file is the file that displays the game.
*/

import java.util.Scanner;

public class Qwixx {
   
 /*a)The Qwixx class is where all the action happens. In this class we have the following
attributes: an array of Dice[] that will contain each of the coloured dice and two white
dice. An array of Players[] that contains the players in the game, four boolean values
that keep track of whether a colour is locked and therefore no longer playable (recall a
colour is locked when any player crosses off the 12 or 2 at the far right of the score pad
of that colour), a static variable NEGPTS which is set to -5. */
   private Dice[] arrayOfDice;
   private Player[] arrayOfPlayer;
   private boolean lockedRed;
   private boolean lockedYellow ;
   private boolean lockedGreen ;
   private boolean lockedBlue ;
   public static int NEGPTS = -5;
 
//these will be used below for specific purposes.
   Scanner keyIn = new Scanner(System.in);
   private Dice diceRed;
   private Dice diceYellow;
   private Dice diceGreen;
   private Dice diceBlue;
   private Dice whiteDice1;
   private Dice whiteDice2 ;
   private Player playerWhite;
   private Player playerColour ;
    
 /*b)A constructor that takes an array of Players and initializes the Players[] with it. The
constructor should also initialize the Dice[].  */
    public Qwixx(Player[] arrayOfNames) {  
    	
      this.arrayOfPlayer = new Player[arrayOfNames.length];
          for(int a=0; a<arrayOfPlayer.length;a++) 
            arrayOfPlayer[a] = arrayOfNames[a];
  
      this.arrayOfDice =new Dice[6];    
        arrayOfDice[0]= new Dice("Red");
        arrayOfDice[1]= new Dice("Yellow");
        arrayOfDice[2]= new Dice("Green");
        arrayOfDice[3]= new Dice("Blue");
        arrayOfDice[4]= new Dice("White1");
        arrayOfDice[5]= new Dice("White2");
      this.diceRed = arrayOfDice[0]; 
      this.diceYellow = arrayOfDice[1];
      this.diceGreen = arrayOfDice[2];
      this.diceBlue = arrayOfDice[3];
      this.whiteDice1 = arrayOfDice[4];
      this.whiteDice2 = arrayOfDice[5];    
    }
   
/*c)The following methods: rollDice() which randomly assigns the current side of each of the 6
dice. A printRolledDice() method that prints all of the dice and their current values. */

    public void rollDice() {
       for(int i = 0; i <arrayOfDice.length;i++)
         arrayOfDice[i].rollDice();
    
    } 
    
    public void printRolledDice() {
	    for(int i = 0; i <arrayOfDice.length;i++){
	      System.out.print(arrayOfDice[i]);
	    }
        System.out.println();
    } 
    
/*d)A playWhiteDiceMove() which takes care of the moves on the white dice. Recall that all
players can use the total number on the two white dice to cross off a number in any row
on their sheet. This method should print the total of the white dice and ask each player if
they would like to use the total to cross of one of the numbers on the game board. If a
player wants to make a move, you should create a move, check if it’s valid and if it is 
update the gameboard. For simplicity, you may loop through the players in the same order
each time. The method might make use helper methods.*/
    public void playWhiteDiceMove() {
    	
        int sumOfWhiteDices = getWhiteDiceTotal();
        System.out.println();
        System.out.println("***** Move on white dice ****");
        System.out.println("The total for the white dice is "+sumOfWhiteDices);
        System.out.println();
        System.out.println(playerWhite.getName()+" it's your turn...");
        playerWhite.printGameBoard();
        System.out.println();
        System.out.println();
        System.out.println("Would you like to cross off a number on the game board using the white dice total? ");
        System.out.print("(anything other than 'yes' is taken to mean no): ");
        String decision = keyIn.next();  
        
        if(decision.toLowerCase().equals("yes")) {
            System.out.print("What colour would you like to cross out? (R = red, Y = yellow, G = green, B = Blue): ");
            String colourName=keyIn.next();
            char colourLetter=colourName.charAt(0);
             
            Move playerTurn = new Move(colourLetter,sumOfWhiteDices);
            
            if(checkValidMove(playerWhite,playerTurn)) {
	          playerWhite.makeMove(playerTurn);
	          checkColourFinished(playerWhite,playerTurn.getColour());
            }
            else
              this.playWhiteDiceMove();
            
            playerWhite.printGameBoard();      
        }     
    }
    
//The following are the helper methods:  
    
//a. A getWhiteDiceTotal()method that returns the sum of the two white dice. 
    private int getWhiteDiceTotal(){
        return (this.whiteDice1.getCurrentSide()+this.whiteDice2.getCurrentSide());
    }

/*b.A checkValidMove(Player p, Move m) method that checks if the move the
player wants to make is valid and returns a boolean indicating whether it is or is
not a valid move. Remember a move is valid if it’s on the gameboard and if the
number being crossed off is further right then the last crossed off number AND
the colour has not been unlocked. Hint: the Player class keeps track of the last
crossed of value for each colour on their own gameboard. */
    private boolean checkValidMove(Player p, Move m) {
  
    int n = m.getNumber();
    char c = m.getColour();
    int choosenColour;
    boolean a = false;
    
    if(n<13 && n>1 ) { 
    	choosenColour = m.convertColourtoNum(c);
    	
    switch (choosenColour) {
    	
    	case 0: {
    			   if(n > p.getLastRedNumber()) { 
    			        if(lockedRed == false) {
    			             p.setRedLast(n);
    			             a=true;
    			          }
    			          else
    			          System.out.println("Can't move on red, it's locked.");  
    		        }
    		        else if(n == p.getLastRedNumber())
    		        System.out.println("Invalid move "+ n +" is already crossed off in R");
    		      
    		        else
    		        System.out.println("Invalid move "+ n ); 
    			}
    	break;
    	case 1: {   
    		        if(n > p.getLastYellowNumber()) { 
    			        if(lockedYellow == false) {
    			            p.setYellowLast(n);
    			            a=true; 
    			        }
    			        else
    			        System.out.println("Can't move on Yellow, it's locked.");
    			    }        
    			    else if(n == p.getLastYellowNumber())
    			    System.out.println("Invalid move "+ n +" is already crossed off in Y");
    			        
    			    else
    			    System.out.println("Invalid move "+ n );      
    			
    	        }
    	break;
    	case 2: {
    				if(n < p.getLastGreenNumber()) {
    		            if(lockedGreen == false) {
    		               p.setGreenLast(n);
    		               a=true;
    		            }
    		            else 
    		            System.out.println("Can't move on Green, it's locked.");
    		        }
    		        else if(n ==p.getLastGreenNumber())
    		        System.out.println("Invalid move "+ n +" is already crossed off in G");
    		   
    		        else
    		        System.out.println("Invalid move "+ n );
    	        }
    	break;
    	case 3: {
					if(n < p.getLastBlueNumber()) {
			            if(lockedBlue == false) {
			              p.setBlueLast(n);
			              a=true;
			            }
			            else
			            System.out.println("Can't move on Blue, it's locked.");    
			        }
			        else if(n == p.getLastBlueNumber())
			        System.out.println("Invalid move "+ n +" is already crossed off in B");
			            
			        else
			        System.out.println("Invalid move "+ n );
    		   }
    	break;
    	}
    }
	else
    System.out.println("Invalid Move,  The number enter should be in between 2 to 12");
		    
    return a;
    }
    
 /*c.A checkColourFinished(Player p, Char colour)method that returns true if
the colour the player has just crossed off a number in becomes finished. The
method should also update that colour to be locked for the game and output this
to the console. */
    private boolean checkColourFinished(Player p, char colour) { 
    boolean a = false;
    
    switch (colour) {
    
    case'R':   if(p.getLastRedNumber() == 12) {
               lockedRed = true;
               System.out.println(" Red is no longer playable. Player "+p.getName()+" has locked it.");
               a = true;
               }
    break;
    case 'Y':  if(p.getLastYellowNumber() == 12) {
               lockedYellow = true;
               System.out.println(" Yellow is no longer playable. Player "+p.getName()+" has locked it.");
               a = true;
               } 
    break;
    case 'G':  if(p.getLastYellowNumber() == 12) {
               lockedYellow = true;
               System.out.println(" Yellow is no longer playable. Player "+p.getName()+" has locked it.");
               a = true;
               }
    break;
    case 'B':  if(p.getLastBlueNumber() == 2) {
               lockedBlue = true;
               System.out.println(" Blue is no longer playable. Player "+p.getName()+" has locked it.");
               a = true;
               }
    break;
    }
    return a;
    }

/*d.A checkGameFinished()method that checks if the game is finished. A game is
finished when the two colour rows have been locked out (by a player
crossing off the 12 or 2 at the far right of the score pad) or a player has passed 4
times (i.e. has -20 points).*/
    private boolean checkGameFinished() {
    	
    boolean a = false;
    
    boolean[] arrayOfLocked = new boolean[4];
    
    arrayOfLocked[0]= lockedRed;
    arrayOfLocked[1]= lockedYellow;
    arrayOfLocked[2]= lockedGreen;
    arrayOfLocked[3]= lockedBlue;
    
    int counter = 0;
    
    for(int b = 0; b <arrayOfLocked.length;b ++) {
	    if(arrayOfLocked[b] == true){
	    counter++;
	    }
    }
    if(counter == 2)
    a = true;
         
    for(int b = 0; b <arrayOfPlayer.length;b++) {      
       
	    if(arrayOfPlayer[b].getNegativePoints() == -20) 
	    a = true;
                  
    }
    return a;
    }
    
 /*e)A playColourDiceMoves() method which takes care of the moves on the colour dice.
Recall that the player who’s turned it is gets to choose a second number to cross off using
1 white die plus one colored die. This method should ask the player if they want to make
a move. If they do, it should ask which white dice they want to use and which colour they
want to cross out, create a move based on this, check if it’s valid and if so update the
gameboard. If they decide not to play, then you should give them -5 points. Again, you
may use the helper methods listed above.*/
    public void  playColourDiceMove() {
        System.out.println(playerColour.getName()+" it's your turn...");
        System.out.println();
        System.out.println("***** Move on any colour dice *****");
        playerColour.printGameBoard();
        System.out.println();
        printRolledDice();
        System.out.println();
        System.out.println("Would you like to cross off a number on the game board using one of the coloured dice ");
        System.out.print("and a white dice? (anything other than 'yes' is taken to mean no): ");
        String decision = keyIn.next();
        
        
        if(decision.toLowerCase().equals("yes")) { 
            System.out.print("Which white dice would you like to you use? (White = 1, White2 = 2): ");
            int numWhite = keyIn.nextInt();
            
            
            int  whiteSelected=0;
            
            if(numWhite == 2)
            whiteSelected = whiteDice2.getCurrentSide();
            
            else if(numWhite == 1) {
            whiteSelected = whiteDice1.getCurrentSide();
            }
            System.out.print("What colour would you like to cross out? (R = red, Y = yellow, G = green, B = Blue): ");
            String colourName= keyIn.next();
            char letter = colourName.charAt(0);
             
            int side = 0;
             
            if (letter == 'R')
               side= diceRed.getCurrentSide();
            else if (letter == 'Y')
               side= diceYellow.getCurrentSide();
            else if (letter == 'G')
               side= diceGreen.getCurrentSide();
            else if (letter == 'B')
               side= diceBlue.getCurrentSide();
             
            Move PlayerTurn;
            PlayerTurn = new Move(letter,(whiteSelected + side));
            
            if(checkValidMove(playerColour,PlayerTurn)){
              playerColour.makeMove(PlayerTurn);
              playerColour.printGameBoard();
              checkColourFinished(playerColour,PlayerTurn.getColour());
            }
            else
            this.playColourDiceMove();
            
        }
        else {
         playerColour.addNegativePoints(-5);
         System.out.println("For passing you get -5 points. You now have "+playerColour.getNegativePoints()+" points.");
        }      
    }

/*f)A play() method which loops calling the rollDice(), printRolledDice(),
playWhiteDiceMove(), playColourDiceMove(), and checkGameFinished()
methods until the game is done. Once the game is done use a method (e.g.
determineWinner()) to determine the winner. */
    public void play() {        
        int t = 0;
        while(!checkGameFinished()) {            
            if(t >= arrayOfPlayer.length){
             t =0;
            }
        System.out.println();
        System.out.println("----- New Round -----");                   
        rollDice();
        printRolledDice();
        for(int p = 0; p<=(arrayOfPlayer.length-1); p++){      
        playerWhite = arrayOfPlayer[p]; 
        
        playWhiteDiceMove();
        }
        System.out.println();
        playerColour = arrayOfPlayer[t];
        
        playColourDiceMove();
        checkGameFinished();
        t++;
        }       
    }
    
 //determineWinner() method.  
    public void determineWinner() {
        for(int i = 0; i <arrayOfPlayer.length;i++)
        System.out.println(arrayOfPlayer[i].getName()+" has a total of: "+arrayOfPlayer[i].getBoardTotalMethod()+" points.");
        
        Player winnerOfTheGame= new Player();
        int points=0;
        for(int a = 0, b = 0; a<arrayOfPlayer.length; a++){
            b = arrayOfPlayer[a].getBoardTotalMethod();
            if (b > points){
            winnerOfTheGame = arrayOfPlayer[a];
            points = arrayOfPlayer[a].getBoardTotalMethod();
            }   
        }
        System.out.println("That's all folks! "+winnerOfTheGame.getName()+" wins the game!");
    }
        
// The Driver class: **MAIN METHOD**
    public static void main(String[] args) {
        
    	Scanner keyIn = new Scanner(System.in);
    	int numOfPlayers=2;

    		//Asking user how many players and ensuring its between 2-5.
    		do {
    			 if (numOfPlayers <2 || numOfPlayers >5)
    			 {System.out.println("You must have between 2 and 5 players.");}
    			
    			 
    		     System.out.print("Please enter the number of players (2-5): ");
    		     numOfPlayers = keyIn.nextInt();
    		     
    		
    		    } while (numOfPlayers <2 || numOfPlayers >5);
    		
           
    
        Player[] gaming = new Player[numOfPlayers ];
        for(int s = 0 ; s<gaming.length; s++){ 
        int value = (s+1);
        System.out.print("Please enter the name of player"+ value +": "); // Asking player for his/her name.
        String gamer = keyIn.next();
        
        Player player = new Player(gamer);
        gaming[s] = player;
        gaming[s].initializeGameboard();
        gaming[s].printGameBoard();
        System.out.println();
        }

        Qwixx myQwixx = new Qwixx(gaming);
        myQwixx.play();
        myQwixx.determineWinner();
        
    }
    
    
}
