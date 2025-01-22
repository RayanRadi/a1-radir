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
}
