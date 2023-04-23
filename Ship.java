public class Ship
{
    // Instance variables
    private int row;
    private int col;
    private int length;
    private int direction;
    private boolean locationSet;
    private boolean directionSet;
    
    // Direction constants
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    
    /**
    Creates a new Ship object with a specified length and default values for its location and direction.
    @param length the length of the ship
    */
    public Ship(int length){
        row = UNSET;
        col = UNSET;
        this.length = length;
        direction = UNSET;
        locationSet = false;
        directionSet = false;
    }
    
    /**
    Checks if the location of the ship has been set.
    @return true if the location has been set, false otherwise.
    */
    public boolean isLocationSet(){
        return locationSet;
    }
    
    /**
    Returns whether the direction of the ship has been set or not.
    @return true if the direction of the ship has been set, false otherwise.
    */
    public boolean isDirectionSet(){
        return directionSet;
    }
    
    /**
    Sets the location of the ship on the game grid.
    @param row the row number of the starting point of the ship
    @param col the column number of the starting point of the ship
    */
    public void setLocation(int row, int col){
        this.row = row;
        this.col = col;
        locationSet = true;
    }
    
    /**
    Sets the direction of the ship.
    @param direction The direction of the ship (0 for horizontal, 1 for vertical)
    */
    public void setDirection(int direction){
        this.direction = direction;
        directionSet = true;
    }
    
    /**
    Returns the row where the front of the ship is located on the game grid.
    If the location has not been set, returns the constant UNSET.
    @return the row where the front of the ship is located or UNSET if the location has not been set.
    */
    public int getRow(){
        return row;
    }
    
    /**
    Returns the row coordinate of the ship's location on the game grid.
    @return the row coordinate of the ship's location
    */
    public int getCol(){
        return col;
    }
    
    /**
    Returns the length of the ship.
    @return an integer representing the length of the ship
    */
    public int getLength(){
        return length;
    }
    
    /**
    Returns the direction of the ship placement.
    @return 0 if horizontal, 1 if vertical, and -1 if not set.
    */
    public int getDirection(){
        return direction;
    }
    
    /**
    Returns the direction of the ship as a string.
    If the direction is horizontal, returns "horizontal".
    If the direction is vertical, returns "vertical".
    If the direction is unset, returns "unset direction".
    @return the direction of the ship as a string
    */
    private String directionToString(){
        if (direction == 0){
            return "horizontal";
        }
        else if (direction == 1){
            return "vertical";
        }
        else{
            return "unset direction";
        }
    }
    
    /**
    Returns a string representation of the ship's location as "row, col" if it is set, or "unset location" otherwise.
    @return a string representation of the ship's location
    */
    private String locationToString(){
        if (locationSet == true){
            return  row + ", " + col;
        }
        else {
            return "unset location";
        }
    }
    
    /**
    Returns a string representation of the ship object.
    @return a string representation of the ship object, including direction, length, and location.
    */
    public String toString(){
         return directionToString() + " ship of length " + length + " at " + "(" + locationToString() + ")";
    }
}
