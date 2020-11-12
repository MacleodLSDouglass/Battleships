package battleshipmodel;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

// Player class contains a default implementation for a player.
// This class could represent a simple human or artificial player.
public class Player {
    // Instance variables.
    protected static int oceanRow = 10;
    protected static int oceanColumn = 10;
    protected static int fleetSize = 5;
    protected static int hitsToWin = 17;
    protected Coordinate[][] ocean;
    protected Ship fleet[];
    private String name;
    protected int hitsOnOpponent;
    protected int missesOnOpponent;
    protected static boolean diagChoice;
    protected static Scanner scan = new Scanner(System.in);
    protected static Random rand = new Random();
    
    // Constructor.
    public Player(String name) {
        this.name = name;
    }
    
    // Enum of constants to determine orientation of ships as they are placed.
    public enum Orientation {
        HORIZONTAL, VERTICAL, LEADING, COUNTER;
        
        // Method to return random Orientation value.
        private static Orientation getRandomOrientation(Random rand) {
            Orientation orient;
            
            if(diagChoice) {
                orient = values()[rand.nextInt(values().length)];
            } else {
                orient = values()[rand.nextInt(values().length - 2)];
            }
            
            return orient;
        }
    }
    
    // Accessor method for ocean.
    protected Coordinate[][] getOcean() {
        return ocean;
    }
    
    // Accessor method for hitsOnOpponent.
    public int getHits() {
        return hitsOnOpponent;
    }
    
    // Accessor method for missesOnOpponent.
    public int getMisses() {
        return missesOnOpponent;
    }
    
    // Method to test if a player has won.
    public boolean hasWon() {
        return hitsOnOpponent == hitsToWin;
    }
    
    // Method used to place ships on a virtual board (ocean coordinates array).
    public void placeFleet() {
        fleet = new Ship[fleetSize];
        // Populate fleet array with ships.
        fleet[0] = new Ship("Aircraft Carrier", 5);
        fleet[1] = new Ship("Battleship", 4);
        fleet[2] = new Ship("Cruiser", 3);
        fleet[3] = new Ship("Submarine", 3);
        fleet[4] = new Ship("Destroyer", 2);
        
        ocean = new Coordinate[oceanRow][oceanColumn];
        // Loop to populate ocean array.
        for(int x = 0; x < ocean.length; x++) {
            for(int y = 0; y < ocean[x].length; y++) {
                ocean[x][y] = new Coordinate();
            }
        }

        // Used regular for loop instead of enhanced to loop through the array
        // so iterations of the loop can be restarted if ships can't be placed.
        for(int x = 0; x < fleet.length; x++){
            
            // Randomise rows and columns
            int randRow = rand.nextInt(oceanRow);
            int randColumn = rand.nextInt(oceanColumn);
            
            // Randomise orientation
            Orientation orientation = Orientation.getRandomOrientation(rand);
            
            // Call method to check if ships can be placed, if they can't, then
            // current loop iteration restarts.
            if(cantPlaceShip(orientation, randRow, randColumn, x)) {
                x--;
                continue;
            }
            
            // Places ships in ocean according to randomised orientation.
            switch(orientation){
                case VERTICAL:
                    for(int y = 0; y < fleet[x].getLength(); y++) {
                        ocean[randRow + y][randColumn].setShip(fleet[x]);
                        }
                    break;
                case HORIZONTAL:
                    for(int y = 0; y < fleet[x].getLength(); y++) {
                        ocean[randRow][randColumn + y].setShip(fleet[x]);
                    }
                    break;
                case LEADING:
                    for(int y = 0; y < fleet[x].getLength(); y++) {
                        ocean[randRow + y][randColumn + y].setShip(fleet[x]);
                    }
                    break;
                case COUNTER:
                    for(int y = 0; y < fleet[x].getLength(); y++) {
                        ocean[randRow + y][randColumn - y].setShip(fleet[x]);
                    }
            }
        }
    }
    
    // Method used to print game board OCEAN_SIZE by OCEAN_SIZE. The showShips
    // parameter is used to hide specific information.
    public void printOcean(boolean showShips) {
        System.out.print("     ");
        
        int b = 0;
        for(char a = 'A'; b < oceanColumn; a++, b++) {
            System.out.print(a);
            System.out.print("   ");
        }
        
        System.out.println();
        
        for(int x = 0; x < oceanRow; x++){
            System.out.print("   ");
            for(int y = 0; y < oceanColumn; y++){
               System.out.print("+---"); 
            }
            System.out.println("+");
            
            if(x < 10) {
                System.out.print(" " + x);
            } else {
                System.out.print(x);
            }
            
            for(int y = 0; y < oceanColumn; y++) {
                System.out.print(" | ");
                
                // Call to access and print coordinate state at the current
                // iteration of the two loops. The showShips parameter is
                // used to differentiate between player and opponent.
                Coordinate coordinate = (Coordinate)ocean[x][y];
                System.out.print(coordinate.printCoordinate(showShips));
            }
            System.out.println(" |");  
        }
        
        System.out.print("   ");
        for(int x = 0; x < oceanColumn; x++){
               System.out.print("+---"); 
            }
        System.out.println("+");
    }
    
    // Method to test if a ship can be placed on the board at a randomised 
    // value. All parameters are tested against ocean state.
    private boolean cantPlaceShip(Orientation orientation, int randRow,
            int randColumn, int x) throws ArrayIndexOutOfBoundsException {
        // Instance variable to return.
        boolean cantPlace = false;
        
        switch (orientation) {
            case VERTICAL:
               for (int y = 0; y < fleet[x].getLength(); y++) {
                // Test to ensure ship is within the ocean with try and catch.
                    try{
                        // Test to ensure ships are not overlapping.
                        if(ocean[randRow + y][randColumn].hasShip()) {
                            cantPlace = true;
                            break;
                        }
                    } catch(ArrayIndexOutOfBoundsException exception) {
                        cantPlace = true;
                        break;        
                    }
            }
            break;
            case HORIZONTAL:
                for (int y = 0; y < fleet[x].getLength(); y++) {
                // Test to ensure ship is within the ocean with try and catch.
                    try{
                        // Test to ensure ships are not overlapping.
                        if(ocean[randRow][randColumn + y].hasShip()) {
                            cantPlace = true;
                            break;
                        }
                    } catch(ArrayIndexOutOfBoundsException exception) {
                        cantPlace = true;
                        break;        
                    }
            }
            case LEADING:
                for (int y = 0; y < fleet[x].getLength(); y++) {
                // Test to ensure ship is within the ocean with try and catch.
                    try{
                        // Test to ensure ships are not overlapping.
                        if(ocean[randRow + y][randColumn + y].hasShip()) {
                            cantPlace = true;
                            break;
                        }
                    } catch(ArrayIndexOutOfBoundsException exception) {
                        cantPlace = true;
                        break;        
                    }
                }
            case COUNTER:
                for (int y = 0; y < fleet[x].getLength(); y++) {
                // Test to ensure ship is within the ocean with try and catch.
                    try{
                        // Test to ensure ships are not overlapping.
                        if(ocean[randRow + y][randColumn - y].hasShip()) {
                            cantPlace = true;
                            break;
                        }
                    } catch(ArrayIndexOutOfBoundsException exception) {
                        cantPlace = true;
                        break;        
                    }
                }
        }
        
        return cantPlace;
    }
    
    public void oceanChoice() {
        System.out.println("How big would you like both oceans to be?");
        
        while(true){
            oceanRow(); oceanColumn();
            
            if((oceanRow * oceanColumn) <= 200 &&
                    (oceanRow * oceanColumn) >= 25) {
                break;
            } else {
                System.out.println("Total number of coordinates is limited "
                        + "(25 minimum, 200 maximum)");
            }
       }
    }
    
    public void oceanRow() {
        int row = -1;
        
        do {  
            row = intCheck("Number of rows: ");
        } while(row == -1);
        
        Player.oceanRow = row;
    }
    
    public void oceanColumn() {
        int column = -1;
    
        do {  
            column = intCheck("Number of columns: ");
            
            if(column > 26) {
                System.out.println("Please enter a number between"
                        + " 1 and 26");
                column = -1;
            }
        } while(column == -1);
    
        Player.oceanColumn = column;
    }
    
    public void diagChoice() {
        System.out.println("Do you want to allow ships to be placed "
                + "diagonally?");
        
        while(true) {    
            int choice = intCheck("1 for yes, 2 for no: ");
            
            if(choice < 1 || choice > 2) {
                System.out.println("Please enter a number between 1 and 2.");
            } else {
                if(choice == 1) {
                diagChoice = true;
                }
                break;
            }
        }
    }
    
    public void hitsToWinChoice() {
        System.out.println("How many hits do you want to allow before the "
                + "game ends?");
        
        while(true) { 
            int choice = intCheck("Number of hits: ");
            
            if(choice < 5 || choice > 17) {
                System.out.println("Please enter a number between 5 and 17.");
            } else {
                hitsToWin = choice;
                break;
            }
        } 
    }
    
    public static int intCheck(String message) throws InputMismatchException {
        while(true){
            System.out.print(message);
            try{
                int a = scan.nextInt();
                return a;
            } catch(InputMismatchException e) {
                System.out.println("Please enter a whole number.");
                scan.next();
            }
        }
    }
}