package battleshipmodel;


// Class ArtificialPlayer contains modified implementation for 
// a computer-controlled opponent.
// Currently this class assumes the same implementation as class Player.
public class ArtificialPlayer extends Player {
    private Difficulty difficulty;
    
    protected enum Difficulty {
        EASY, MEDIUM, HARD;
    }
    // Constructor, called from parent class.
    public ArtificialPlayer(String name) {
        super(name);
    }
    
    // Method to randomise a row and column value with which to score
    // against the virtual board of the opponent.
    public void takeGuess(HumanPlayer opponent){
        
        //Instance variables.
        int randRow, randColumn = 0;
        
        // Loop to randomise values then test whether coordinate at those values
        // has been hit.
        while(true){
            randRow = rand.nextInt(oceanRow);
            randColumn = rand.nextInt(oceanColumn);
            
            if(opponent.ocean[randRow][randColumn].isHit() == false) { 
                break;
            }
        }
        
        // Checks whether randomised values hit or missed a ship on the virtual
        // board.
        if(opponent.ocean[randRow][randColumn].tryHit()) {
            System.out.print("Opponent has scored a hit!");
            hitsOnOpponent++;
        }
        else {
            System.out.print("Opponent missed!");
            missesOnOpponent++;
        } 
    }
    
    public void difficultyChoice() {
        System.out.println("What difficulty would you like to play on?");
        
        while(true) {    
            int choice = intCheck("1 for easy, 2 for medium, 3 for hard: ");
            
            if(choice < 1 || choice > 3) {
                System.out.println("Please enter a number between 1 and 3.");
            } else {
                switch(choice) {
                    case 1:
                        difficulty = Difficulty.EASY;
                        break;
                    case 2:
                        difficulty = Difficulty.MEDIUM;
                        break;
                    case 3:
                        difficulty = Difficulty.HARD;
                }
                break;
            }
        }
    }
}