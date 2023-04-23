import java.util.*;

public class Player
{
    // Instance variables
    private Ship[] ships;
    private Grid own = new Grid();
    private Grid opp = new Grid();
    private int shipNum1 = 0;
    private int shipNum2 = 0;
    
    // Length constants
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    /**
    Constructs a new Player object with an array of Ships, where each Ship has a length specified in the SHIP_LENGTHS array.
    */
    public Player(){
        ships = new Ship[SHIP_LENGTHS.length];
        for (int x = 0; x < SHIP_LENGTHS.length; x++) {
            ships[x] = new Ship(SHIP_LENGTHS[x]);
        }
    }
    
    /**
    Returns the Ship object corresponding to the specified index in the array of ships.
    @param num the index of the Ship object to be returned
    @return the Ship object corresponding to the specified index
     */
    public Ship getShip(int num){
        return ships[num];
    }
    
    /**
    Returns the player's own Grid object.
    @return the Grid object representing the player's own game board
    */
    public Grid getGrid(){
        return own;
    }
    
    /**
    Prints the grid of the player's own ships, indicating the location of the ships with "X" and empty cells with "-".
    */
    public void printMyShips(){
        own.printShips();
    }
    
    /**
    Prints the status of the player's own grid, with only hits and misses shown.
    The opponent's ships are not displayed.
    */
    public void printOpponentGuesses(){
        own.printStatus();
    }
    
    /**
    Prints the current status of the player's guesses on the opponent's grid.
    */
    public void printMyGuesses(){
        opp.printStatus();
    }
    
    /**
    Records the opponent's guess at the given row and column coordinates.
    Marks the corresponding location on this player's grid as a hit or miss, depending on whether there is a ship at that location.
    @param row the row coordinate of the guess
    @param col the column coordinate of the guess
    @return true if there is a ship at the guessed location, false otherwise
    */
    public boolean recordOpponentGuess(int row, int col){
        if(own.hasShip(row, col)){
            own.markHit(row, col);
            return true;
        }
        else{
            own.markMiss(row, col);
            return false;
        }
    }
    
    /**
    Records a guess made by this player at the given row and column coordinates.
    Marks the corresponding location on this opponent's grid as a hit or miss, depending on whether there is a ship at that location.
    @param row the row of the guess
    @param col the column of the guess
    @return true if the guess was a hit, false otherwise
    */
    public boolean recordMyGuess(int row, int col){
        if(opp.hasShip(row, col)){
            opp.markHit(row, col);
            return true;
        }
        else{
            opp.markMiss(row, col);
            return false;
        }
    }
    
    /**
    Chooses a location and direction for a ship and adds it to the player's own grid.
    @param s the ship to be placed
    @param row the row number of the starting location
    @param col the column number of the starting location
    @param direction the direction of the ship (0 for horizontal, 1 for vertical)
    */
    public void chooseShipLocation(Ship s, int row, int col, int direction){
        // checks whether the player has already placed the maximum number of allowed ships
        if (shipNum1 < 5){
            s.setLocation(row, col);
            s.setDirection(direction);
            own.addShip(s);
            shipNum1++;
        }
    }
    
    /**
    Chooses a location and direction for a ship and adds it to the player's opponent grid so when it is guessed it will show.
    @param s the ship to be placed
    @param row the row number of the starting location
    @param col the column number of the starting location
    @param direction the direction of the ship (0 for horizontal, 1 for vertical)
    */
    public void placeEnemyShipLocation(Ship s, int row, int col, int direction){
        if (shipNum2 < 5){
            s.setLocation(row, col);
            s.setDirection(direction);
            opp.addShip(s);
            shipNum2++;
        }
    }
}
