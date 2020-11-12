package battleshipmodel;

// Class Coordinate contains all implementation for one coordinate of the ocean
// for any player. Can be referenced when dealing with a player's ocean or
// the opponent's "target" ocean.
public class Coordinate {
    // Instance variables.
    private final String HIT = "x";
    private final String MISS = "-";
    private final String EMPTY = " ";
    private final String UNKNOWN = " ";
    private Ship ship = new Ship(null, -1);
    private boolean isHit = false;
    
    // Constructor.
    public Coordinate(){
    }
    
    // Ship mutator method.
    protected void setShip(Ship ship){
        this.ship = ship;
    }
    
    // Method returns true if coordinate has a ship, checks ship length.
    protected boolean hasShip() {
        return ship.getLength() > -1;
    }
    
    // isHit accessor method.
    protected boolean isHit() {
        return isHit;
    }
    
    // Method used in takeGuess() to hit a coordinate and return if that
    // coordinate had a ship.
    protected boolean tryHit() {
        this.isHit = true;
        return hasShip();
    }
    
    // Method used to print information from player ocean array virtual board
    // allowing the player to assess the state of coordinates. Some information
    // is hidden for the board of the opponent. The showShip parameter
    // differentiates between player and opponent.
    protected String printCoordinate(boolean showShip) {
        //Instance variable to return.
        String coordinate = "";
        
        if(showShip) {
            if(isHit() == true && hasShip() == true) {
                coordinate = HIT;
            }
            else if(isHit() == true && hasShip() == false) {
                coordinate = MISS;
            }
            else if(isHit() == false && hasShip() == true) {
                coordinate = Character.toString(ship.getInitial());
            }
            else if(isHit() == false && hasShip() == false) {
                coordinate = EMPTY;
            }
        }
        else {
            if(isHit() == true && hasShip() == true) {
                coordinate = HIT;
            }
            else if(isHit() == true && hasShip() == false) {
                coordinate = MISS;
            }
            else if(isHit() == false && hasShip() == true) {
                coordinate = UNKNOWN;
            }
            else if(isHit() == false && hasShip() == false) {
                coordinate = UNKNOWN;
            }
        }
        
        return coordinate;
    }
}