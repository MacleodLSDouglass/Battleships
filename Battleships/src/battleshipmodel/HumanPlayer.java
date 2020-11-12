package battleshipmodel;

// Class HumanPlayer contains modified implementation for a human-controlled 
// player.
public class HumanPlayer extends Player {
    final static int COLUMN_OFFSET = 65;
    
    // Constructor, called from parent class.
    public HumanPlayer(String name) {
        super(name);
    }
    
    // Method to accept a row and column from the player, check if the input is
    // valid, then score the guess against the ocean virtual board of the
    // opponent. The player has to try again if the input is invalid and
    // is advised on how to input values correctly.
    public void takeGuess(ArtificialPlayer opponent) {
        
        // Instance variables.
        boolean coordinateNotHit = false;
        
        int choiceRow, choiceCol = 0;
        
        // Loop to test if coordinate at input value has been hit already
        // (can't hit same coordinate twice).
        coordinateTest: // Label for first loop to reference within second loop.
        do{
            // Loop to accept input value, then check if it is valid (an 
            // integer, and within the confines of the grid). If invalid input
            // is entered at column input, loop goes back to row input allowing
            // row to be reentered if user misentered row input.
            while(true){
                choiceRow = intCheck("Guess a row: ");
                
                if (choiceRow < 0 || choiceRow > oceanRow){
                    System.out.print("Please enter a number between 0 "
                            + "and " + (oceanRow - 1) + ".\n");
                } else {
                    break;
                }
            }

            while(true) {
                System.out.print("Guess a column: ");
                String choiceColStr = scan.next();
                
                if(choiceColStr.matches("[A-Za-z]{1}")) {
                    char choiceColChar = choiceColStr.charAt(0);

                    if(choiceColChar >= 'a' && choiceColChar <= 'z') {
                        choiceColChar = Character.toUpperCase(choiceColChar);
                    }
                    if(choiceColChar < 'A' || 
                            choiceColChar > 
                            (char)(oceanColumn + COLUMN_OFFSET - 1)) {
                        System.out.println("Please enter a letter between A and"
                                + " "+ (char)(oceanColumn + COLUMN_OFFSET - 1));
                        continue coordinateTest;
                    }
                    choiceCol = choiceColChar - COLUMN_OFFSET;
                    break;
                } else {
                System.out.println("Please enter a letter between A and "
                        + (char)(oceanColumn + COLUMN_OFFSET - 1));
                continue coordinateTest;
                }
            }
            
            if(opponent.ocean[choiceRow][choiceCol].isHit()) {
                System.out.println("Coordinate has already been chosen."
                        + " Please choose another.\n");
            }
            else {
                coordinateNotHit = true;
            }
            
        } while(coordinateNotHit == false);
        
        // Checks whether input values hit or missed a ship on the virtual
        // board.
        if(opponent.ocean[choiceRow][choiceCol].tryHit()) {
            System.out.print("You have scored a hit!");
            hitsOnOpponent++;
        }
        else {
            System.out.print("You missed!");
            missesOnOpponent++;
        }           
    }
}