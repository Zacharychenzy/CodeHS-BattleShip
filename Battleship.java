import java.util.*;

public class Battleship extends ConsoleProgram
{
    // Direction constants
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    
    // Instance variables
    static int guessRow = UNSET;
    static int guessCol = UNSET;
    static int row = UNSET;
    static int col = UNSET;
    static int direction = UNSET;
    
    static Player player = new Player();
    static Player enemy = new Player();
    
    static Scanner sc = new Scanner(System.in);
    
    /**
    Runs the game by calling various methods in a particular sequence to set up and play the game.
    */
    public void run()
    {
        intro();
        
        placeShips();
       
        placeEnemyShips();
        
        printYourShips();
        
        printComputerShips();
        
        playGame();
    }
    
    /**
    Prints an introduction message for the Battleship game.
    Provides a brief description of the game's rules and objectives.
    */
    public static void intro() {
        System.out.println("Welcome to Battleship!");
        System.out.println("In this game, you will play against the computer.");
        System.out.println("The game is played on a 10x10 grid.");
        System.out.println("Each player will place their ships on the grid, and then take turns guessing where the opponent's ships are.");
        System.out.println("The first player to sink all of their opponent's ships wins!");
        System.out.println("");
    }
    
    /**
    This method allows the player to place their five ships on the game grid.
    The method prompts the user to input the starting location and direction of each ship,
    and checks whether the placement is valid and does not overlap with previously placed ships.
    The method uses the chooseShipLocation() method to place the ship on the game grid.
    */
    public static void placeShips() {
        // Loop through each ship to be placed
        for (int x = 0; x < 5; x++) {
            boolean validInput = false;
            // Keep asking for input until valid input is received
            while (!validInput) {
                try {
                    // Get starting location input from user
                    System.out.print("Enter the starting location (e.g. A2): ");
                    String input = sc.next().toUpperCase();
                    
                    // Check if the input is a 10, which is invalid
                    if (input.length() == 3 && input.charAt(1) == '1' && input.charAt(2) == '0'){
                    }
                    // Check if the input is of length 2 (e.g. A2)
                    else if (input.length() != 2) {
                        throw new Exception();
                    }
                    
                    // Convert the row letter to an int (0-9) and get the column number (0-9)
                    int row = input.charAt(0) - 'A';
                    int col = Integer.parseInt(input.substring(1)) - 1;
                    
                    // Check if the row and column numbers are within bounds (0-9)
                    if (!(row >= 0 && row <= 9 && col >= 0 && col <= 9)) {
                        throw new Exception();
                    }
                    
                    // Get the direction input from the user (0 for horizontal, 1 for vertical)
                    System.out.print("Enter the direction (0 for horizontal, 1 for vertical): ");
                    int direction = sc.nextInt();
                    
                    // Check if the direction input is valid (0 or 1)
                    if (direction == 0 || direction == 1) {
                        // Get the ship object to be placed
                        Ship s = player.getShip(x);
                        
                        // Check if the ship placement overlaps with another ship
                        if (!isShipOverlap(player, row, col, s.getLength(), direction)) {
                            // Place the ship at the chosen location
                            player.chooseShipLocation(s, row, col, direction);
                            enemy.placeEnemyShipLocation(s, row, col, direction);
                            validInput = true;
                        }
                        else {
                            System.out.println("Ship placement overlaps with another ship. Please choose a different location.");
                        }
                    }
                    else {
                        throw new Exception();
                    }
                }
                // Display error message for invalid input
                catch (Exception e) {
                    System.out.println("Invalid input. Please enter a row letter between A and J, followed by a number between 1 and 10, and a direction of 0 or 1.");
                }
            }
        }
    }
    
    /**
    Randomly places enemy ships on the grid.
    */
    public static void placeEnemyShips() {
        // Loop through each ship to be placed
        for (int x = 0; x < 5; x++) {
            
            // get the x-th ship of the enemy player
            Ship s = enemy.getShip(x);
            
            // get the length of the ship
            int shipLength = s.getLength();
            
            // randomly choose a direction (0 for horizontal, 1 for vertical)
            int randomDirection = Randomizer.nextInt(2);
            
            boolean shipPlaced = false;
            // Keep checking until valid random row and col is generated
            while (!shipPlaced) {
                // randomly choose a starting row and column position
                int randomRow = Randomizer.nextInt(10 - shipLength);
                int randomCol = Randomizer.nextInt(10 - shipLength);
                
                // check if the chosen position does not overlap with an existing ship and is not adjacent to another ship
                if (!enemy.getGrid().hasShip(randomRow, randomCol) &&
                    !isShipOverlap(enemy, randomRow, randomCol, shipLength, randomDirection)) {
                    // place the ship
                    enemy.chooseShipLocation(s, randomRow, randomCol, randomDirection);
                    player.placeEnemyShipLocation(s, randomRow, randomCol, randomDirection);
                    shipPlaced = true;
                }
            }
        }
    }
    
    /**
    Starts and runs the game of Battleship.
    Displays the introduction message and then calls methods to place ships and start the game.
    */
    public static void playGame() {
        // Initialize variables for tracking win and turn number
        boolean win = false;
        int turn = 0;
        
        // Continue game until someone wins
        while (!win) {
            // Alternate turns between player and computer
            if (turn % 2 == 0) {
                System.out.println("Now it's your turn to guess.");
                // Prompt player for guess
                askForGuess();
                // Print player's guess and ship status
                printYourStatus();
                // Check if all of computer's ships are sunk
                win = allShipsSunk(player);
            }
            else {
                System.out.println("It's the computer's turn to guess.");
                // Computer randomly selects a guess
                computerGuess();
                // Update your guess and ship status
                printYourStatus();
                // Check if all of player's ships are sunk
                win = allShipsSunk(enemy);
            }
            turn++;
        }
        
        // Check who won and display message accordingly
        if (turn % 2 == 0) {
            System.out.println("Congratulations, you won the game!");
        }
        else {
            System.out.println("Sorry, you lost the game. Better luck next time!");
        }
    }

    /**
    Asks the player to input a guess location and records the guess. If the guess hits an enemy ship, prints a message
    indicating a hit. If the guess misses, prints a message indicating a miss.
    */
    public static void askForGuess() {
        boolean validInput = false;
        // Loop until valid input is entered
        while (!validInput) {
            try {
                // Prompt user to enter a valid guess location
                System.out.print("Enter a valid location (e.g. A2): ");
                String input = sc.next().toUpperCase();
                
                // Check if input is valid
                if (input.length() == 3 && input.charAt(1) == '1' && input.charAt(2) == '0'){
                    // Do nothing, valid input
                }
                else if (input.length() != 2) {
                    throw new Exception();
                }
                
                // Convert input to row and column values
                int row = input.charAt(0) - 'A';
                int col = Integer.parseInt(input.substring(1)) - 1;
                
                // Throw exception if input is out of bounds
                if (row < 0 || row >= 10 || col < 0 || col >= 10) {
                    throw new Exception();
                }
                
                // Check if the cell has already been guessed
                if (!enemy.getGrid().alreadyGuessed(row, col)){
                    // Record user's guess
                    player.recordMyGuess(row, col);
                    
                    // Record opponent's guess and check if it hits a ship
                    if (enemy.recordOpponentGuess(row, col)) {
                        System.out.println("You've hit a ship!");
                    }
                    else {
                        System.out.println("You've missed");
                    }
                    validInput = true;
                }
                else {
                    System.out.println("This location has been hit");
                }
            }
            // Display error message for invalid input
            catch (Exception e) {
                System.out.println("Invalid location. Please enter a letter A-J followed by a number 1-10.");
            }
        }
        System.out.println("");
    }

    /**
    Starts the game by placing the ships for both the player and the computer,
    and then allows the player and the computer to take turns guessing the location
    of each other's ships until all of one player's ships are sunk.
    Displays the result of the game after it ends.
    */
    public static void computerGuess() {
        // initialize variables
        boolean validInput = false;
        int randomRow = UNSET;
        int randomCol = UNSET;
        
        // Generate random coordinates until a valid guess is found
        while(!validInput){
            // Generate random row and column
            randomRow = Randomizer.nextInt(10);
            randomCol = Randomizer.nextInt(10);
            
            // Check if the cell has already been guessed
            if (!enemy.getGrid().alreadyGuessed(randomRow, randomCol)){
                validInput = true;
            }
        }
        // Record the guess and print the appropriate message
        enemy.recordMyGuess(randomRow, randomCol);
        
        // Check if the guess hit a player's ship
        if (player.recordOpponentGuess(randomRow, randomCol)){
            System.out.println("Computer's hit your ship!");
        }
        else {
            System.out.println("Computer's missed");
        }
        System.out.println("");
    }
    
    /**
    Checks if placing a ship of given length in the given direction from the specified starting
    row and column on the given player's grid would overlap with any existing ship.
    @param player the player for which to check for ship overlap
    @param row the starting row for the ship
    @param col the starting column for the ship
    @param length the length of the ship to be placed
    @param direction the direction in which to place the ship (0 for horizontal, 1 for vertical)
    @return true if placing the ship would overlap with any existing ship, false otherwise
    */
    private static boolean isShipOverlap(Player player, int row, int col, int length, int direction) {
        for (int i = 0; i < length; i++) {
            // Check if ship already exists at the current location in the specified direction
            //horizontal
            if (direction == 0) {
                if (player.getGrid().hasShip(row, col + i)) {
                    return true;
                }
            } 
            //vertical
            else {
                if (player.getGrid().hasShip(row + i, col)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    Checks if all the ships of a given player have been sunk.
    @param player the player whose ships need to be checked
    @return true if all the ships of the player have been sunk, false otherwise
    */
    public static boolean allShipsSunk(Player player) {
        for (int x = 0; x < 5; x++) {
            // loop through each ship of the player
            Ship ship = player.getShip(x);
            for (int y = 0; y < ship.getLength(); y++) {
                // loop through each position of the ship
                int row = ship.getRow();
                int col = ship.getCol();
                // if the ship is horizontal, increment the column
                if (ship.getDirection() == HORIZONTAL) {
                    col += y;
                }
                // otherwise, increment the row
                else {
                    row += y;
                }
                // check if the position has been guessed before
                if (!player.getGrid().alreadyGuessed(row, col)) {
                    // if at least one position is not guessed, return false
                    return false;
                }
            }
        }
        // if all positions of all ships are guessed, return true
        return true;
    }
    
    /**
    Prints the player's own ships on the console.
    */
    public static void printYourShips(){
        System.out.println("Your Ships");
        player.printMyShips();
        System.out.println();
    }
    
    /**
    Prints the computer's ships in the console.
    */
    public static void printComputerShips(){
        System.out.println("Computer's Ships");
        enemy.printMyShips();
        System.out.println();
    }
    
    /**
    Prints the current status of the player, including their previous guesses and the status of their own ships.
    */
    public static void printYourStatus() {
        System.out.println("Your Guesses");
        player.printMyGuesses();
        System.out.println();
        System.out.println("Your Status");
        player.printOpponentGuesses();
        System.out.println();
    }
    
    /**
    Prints the computer's guesses and status, including their previous guesses and the status of their own ships.
    */
    public static void printComputerStatus() {
        System.out.println("Computer's Guesses");
        enemy.printMyGuesses();
        System.out.println();
        System.out.println("Computer's Status");
        enemy.printOpponentGuesses();
        System.out.println();
    }
    
    /**
    Clears the console screen.
    */
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
