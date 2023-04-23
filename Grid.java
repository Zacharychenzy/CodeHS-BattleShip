public class Grid
{
    // Instance variables
    private Location[][] grid;
    
    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    /**
    Constructor for the Grid class.
    Initializes the grid as a 2D array of Location objects and sets them all to be unguessed and not containing a ship.
    */
    public Grid(){
        grid = new Location[NUM_ROWS][NUM_COLS];
        for (int y = 0; y < NUM_ROWS; y++) {
            for (int x = 0; x < NUM_COLS; x++) {
                grid[x][y] = new Location();
            }
        }
    }
    
    /**
    Marks a hit at the specified row and column of the grid.
    @param row the row to mark as a hit
    @param col the column to mark as a hit
    */
    public void markHit(int row, int col){
        grid[row][col].markHit();
    }
    
    /**
    Mark the specified location as a miss.
    @param row the row of the location to mark
    @param col the column of the location to mark
    */
    public void markMiss(int row, int col){
        grid[row][col].markMiss();
    }
    
    /**
    Sets the status of the location at the specified row and column.
    @param row the row of the location to set the status of
    @param col the column of the location to set the status of
    @param status the status to set the location to
    */
    public void setStatus(int row, int col, int status){
        grid[row][col].setStatus(status);
    }
    
    /**
    Returns the status of the Location at the specified row and column on the grid.
    @param row the row of the Location
    @param col the column of the Location
    @return the status of the Location at the specified row and column
    */ 
    public int getStatus(int row, int col){
        return grid[row][col].getStatus();
    }
    
    /**
    Checks if a specific location on the grid has already been guessed.
    @param row the row index of the location to check
    @param col the column index of the location to check
    @return true if the location has already been guessed, false otherwise
    */
    public boolean alreadyGuessed(int row, int col){
        return !grid[row][col].isUnguessed();
    }
    
    /**
    Sets the "hasShip" value of the Location object at the given row and column in the grid.
    @param row The row of the Location object to set the "hasShip" value for.
    @param col The column of the Location object to set the "hasShip" value for.
    @param val The value to set for the "hasShip" field of the Location object.
    */
    public void setShip(int row, int col, boolean val){
        grid[row][col].setShip(val);
    }
    
    /**
    Checks if there is a ship at the specified row and column in the grid.
    @param row the row of the location to check
    @param col the column of the location to check
    @return true if there is a ship at the specified location, false otherwise
    */
    public boolean hasShip(int row, int col){
        return grid[row][col].hasShip();
    }
    
    /**
    This method returns the Location object located at the specified row and column in the grid.
    @param row the row of the desired Location object
    @param col the column of the desired Location object
    @return the Location object located at the specified row and column in the grid
    */
    public Location get(int row, int col){
        return grid[row][col];
    }
    
    /**
    Returns the number of rows in the grid.
    @return the number of rows in the grid.
    */
    public int numRows(){
        return NUM_ROWS;
    }
    
    /**
    Returns the number of columns in the grid.
    @return the number of columns in the grid.
    */
    public int numCols(){
        return NUM_COLS;
    }
    
    
    /**
    Prints the current status of the grid.
    Uses X to indicate a hit, O to indicate a miss, and - to indicate an unguessed location.
    The rows are labeled A-J and the columns are labeled 1-10.
    */
    public void printStatus(){
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        String[] alph = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int x = 0; x < NUM_ROWS; x++){
            System.out.print(alph[x] + " ");
            for (int y = 0; y < NUM_COLS; y++){
                if (grid[x][y].checkHit()){
                    System.out.print("X" + " ");
                }
                else if (grid[x][y].checkMiss()){
                    System.out.print("O" + " ");
                }
                else{
                    System.out.print("-" + " ");
                }
            }
            System.out.println("");
        }
    }
    
    /**
    Prints the current state of the grid with the locations of all ships displayed as X's.
    Unoccupied locations are displayed as hyphens.
    */
    public void printShips(){
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        String[] alph = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int x = 0; x < NUM_ROWS; x++){
            System.out.print(alph[x] + " ");
            for (int y = 0; y < NUM_COLS; y++){
                if (grid[x][y].hasShip()){
                    System.out.print("X" + " ");
                }
                else{
                    System.out.print("-" + " ");
                }
            }
            System.out.println("");
        }
    }
    
    /**
    Adds a ship to the grid.
    @param s the ship to add to the grid
    */
    public void addShip(Ship s){
        int row = s.getRow();
        int col = s.getCol();
        int length = s.getLength();
        int direction = s.getDirection();
        for (int x = 0; x < length; x++){
            //horizontal
            if (direction == 0){
                setShip(row, col + x, true);
            }
            //vertical
            else{
                setShip(row + x, col, true);
            }
        }
    }
}
