import java.util.ArrayList;
/**
 * Conway's Game of Life Class which holds various methods that will
 * progress the state of the board through it's many iterations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    //instance variables
    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;
    private boolean[][] grid;
    private int totalAliveCells;

    /**
    * Static Constructor(basic) which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file takes in a file which will contain the information of
    * the size of the grid, the number of alive cells and the locations
    * of those cells
    */
    public GameOfLife(String file) {
        
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid() {

    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells() {

    }

    /**
     * Accesses a cell in the grid and returns the status of the cell, ALIVE or DEAD
     * @param col column position of the cell
     * @param row row position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState(int row, int col) {

    }

    /**
     * Checks to see if there are any alive cells in the grid to determine if it is stable or not
     * @return true if there is at least one cell alive otherwise false
     */
    public boolean isAlive() {

    }

    /**
     * Determines the int value of the number of alive cells around a given cell
     * @param col column position of the center cell
     * @param row row position of the center cell
     * @return neighboringCells, the number of alive cells at most 8
     */
    public int numOfAliveNeighbors(int row, int col) {

    }

    /**
     * Computes the next generation of the grid using
     * the rules for Conway's Game of Life
     * @return boolean[][] of new grid
     */
    public boolean[][] computeNewGrid() {

    }

    /**
     * Sets the grid instance variable to the new computed grid after one generation
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration() {

    }

    /**
     * Sets the grid instance variablw with the new computed grid after multiple gnerations
     * @param steps number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration(int steps) {

    }

    /**
     * Determines the number of separate communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

    }
}
