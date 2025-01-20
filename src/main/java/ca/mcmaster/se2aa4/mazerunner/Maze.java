package ca.mcmaster.se2aa4.mazerunner;

/**
 * Represents the maze structure.
 */
public class Maze {
    private char[][] grid; // 2D representation of the maze (walls and passages)

    /**
     * Constructor for the Maze class
     * TO DO: Implement logic to initialize the grid from a file or input.
     */
    public Maze(char[][] grid) {
        this.grid = grid;
    }

    /**
     * width of the maze .
     */
    public int getWidth() {
        // TODO: Return the width of the grid.
        return grid[0].length;
    }

    /**
     *  height of the maze 
     */
    public int getHeight() {
        // TODO: return the height 
        return grid.length;
    }

    /**
     * Gets the cell at a given position.
     * param x Column index.
     * param y Row index.
     * return The character at the specified position.
     */
    public char getCell(int x, int y) {
        return grid[y][x];
    }
}
