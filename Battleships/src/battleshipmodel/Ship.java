package battleshipmodel;

// Class Ship contains all implementation for a ship of any size.
public class Ship {

    // Implementation for this class is already provided as follows.
    
    // Instance variables.
    private String name;
    private int length;
    
    // Constructor.
    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }
    
    // Method getName(): Accessor method.
    protected String getName() {
        return name;
    }
    
    // Method getInitial(): Calculates an initial to represent the ship.
    // Returns '?' if the initial cannot be obtained due to an empty name.
    protected char getInitial() {
        return name.equals("") ? '?' : name.charAt(0);
    }
    
    // Method getLength(): Accessor method.
    protected int getLength() {
        return length;
    }
}