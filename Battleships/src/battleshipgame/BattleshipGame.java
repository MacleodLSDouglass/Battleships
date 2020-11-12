// This battleship game will generate two fleets of ships based on the ships:
// "Aircraft Carrier" of length 5, "Battleship of length 4, "Cruiser" of length
// 3, "Submarine" of length 3 and "Destroyer" of length 2. It will then assign
// the fleets to randomised coordinate locations on two virtual respective
// grids, one for the opponent and one for the player. The program will then
// print the grids and prompts the player for a coordinate that they want to
// test for a ship of the opponent. This goes on until either the player hits
// all ship coordinates of the opponent, or the opponent does likewise, both
// opponent and player can win at the same time.

// Macleod Douglass 18482867

package battleshipgame;

import battleshipmodel.*;
import java.util.Scanner;

// The BattleshipGame class contains the driver implementation
// for the Battleship game.
public class BattleshipGame {
    static Scanner scan = new Scanner(System.in);
    
    // Driver method.
    public static void main(String[] args) {
        // Create players and print welcome message.
        HumanPlayer player1 = new HumanPlayer("Player 1");
        ArtificialPlayer player2 = new ArtificialPlayer("Player 2");
        
        System.out.println("Welcome to the Battleship game.\n");

        if (!acceptDefault()) {
            player1.oceanChoice();
            player1.diagChoice();
            player1.hitsToWinChoice();
            player2.difficultyChoice();
        }
        player1.placeFleet();
        player2.placeFleet();

        System.out.println("Thank you for your selection. Good luck and "
                + "have fun!\n");
        
        // Print the grids of both players
        System.out.println("Target grid (opponent):");
        player2.printOcean(false);
        
        System.out.println("Ocean grid (you):");
        player1.printOcean(true);
        
        System.out.println();
        
        // Begin loop that allows the player and opponent to take turns until
        // either player sinks all of their opponents ships.
        int x = 0;
        
        while(player1.hasWon() == false && player2.hasWon() == false) {
            System.out.println("Turn " + (x + 1) + "...");
            
            // Player takes guess then the score of hits and misses is reported.
            player1.takeGuess(player2);
            System.out.println(" Progress: " + player1.getHits() +
                    " hits and " + player1.getMisses() + " misses.");
            
            // Opponent takes guess then the score of hits and misses
            // is reported.
            player2.takeGuess(player1);
            System.out.println(" Progress: " + player2.getHits() +
                    " hits and " + player2.getMisses() + " misses.");
            
            System.out.println();
            
            // Updated grids are printed.
            System.out.println("Target grid (opponent):");
            player2.printOcean(false);
        
            System.out.println("Ocean grid (you):");
            player1.printOcean(true);
            
            // Check to see if someone has won.
            if(player1.hasWon()){
                System.out.println("You have won!");
            }
            if(player2.hasWon()) {
                System.out.println("Opponent has won.");
            }
            if(player1.hasWon() == false && player2.hasWon() == false) {
            System.out.println("Nobody has won yet. Keep playing...\n");
            }
            
            x++;
        }
      
        System.out.println("Thanks for playing!");
    }
    
    private static boolean acceptDefault() {     
        System.out.println("Would you like to play with default settings"
                + " or choose the settings?");
        
        while(true) {
            int choice = Player.intCheck("1 for default, 2 to choose settings: ");
            
            if(choice < 1 || choice > 2) {
                System.out.println("Please enter a number between 1 and 2.");
            } else {
                return choice == 1;
            }
        }
    }
}