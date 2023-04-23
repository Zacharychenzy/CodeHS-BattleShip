public class Location
{
    // Instance variables
    private int status;
    private boolean hasShip;
    
    // Status constants
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    
    /**
    Constructs a new Location object with status set to 0 and hasShip set to false.
    */
    public Location(){
        status = UNGUESSED;
        hasShip = false;
    }
    
    /**
    This method checks if the location has been hit or not. If the status of the location is equal to the constant HIT, it returns true; otherwise, it returns false.
    @return true if the location has been hit, false otherwise.
    */
    public boolean checkHit(){
        return status == HIT;
    }
    
    /**
    Checks if the location has been marked as a missed shot.
    @return true if the location has been marked as a missed shot, false otherwise.
    */
    public boolean checkMiss(){
        return status == MISSED;
    }
    
    /**
    Check if the location has not been guessed yet.
    @return true if the location is unguessed, false otherwise
    */
    public boolean isUnguessed(){
        return status == UNGUESSED;
    }
    
    /**
    Marks the location as hit by changing its status to HIT.
    */
    public void markHit(){
        status = HIT;
    }
    
    /**
    Marks the Location as a missed shotby changing its status to MISSED.
    */
    public void markMiss(){
        status = MISSED;
    }
    
    /**
    Returns whether this location has a ship or not.
    @return true if the location has a ship, false otherwise
    */
    public boolean hasShip(){
        return hasShip;
    }
    
    /**
    Sets whether or not a ship is present at this location.
    @param val true if a ship is present, false otherwise
    */
    public void setShip(boolean val){
        hasShip = val;
    }
    
    /**
    Sets the status of the location.
    @param status an integer representing the status to be set for the location
    */
    public void setStatus(int status){
        this.status = status;
    }
    
    /**
    Returns the current status of the location.
    @return An integer representing the current status of the location.
    */
    public int getStatus(){
        return status;
    }
}
