package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private char[][] grid;

    public Maze() {
        // Hardcoded straight-line maze for MVP
        this.grid = new char[][] {
            {'#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'}
        };
    }

    public char[][] getGrid() {
        return grid;
    }

    public int[] getEntryPoint() {
        return new int[] {1, 0}; // Entry point (row 1, column 0)
    }

    public int[] getExitPoint() {
        return new int[] {1, grid[1].length - 1}; // Exit point (row 1, last column)
    }
}
