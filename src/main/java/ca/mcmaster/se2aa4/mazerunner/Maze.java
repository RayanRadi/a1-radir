package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.IOException;

public class Maze {
    private char[][] grid;

    public Maze(BufferedReader reader) throws IOException {
        grid = reader.lines().map(String::toCharArray).toArray(char[][]::new);
    }

    public char[][] getGrid() {
        return grid;
    }

    public int[] getEntryPoint() {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][0] == ' ') return new int[] {row, 0}; // Entry on the left
        }
        throw new IllegalArgumentException("No entry point found on the left border.");
    }

    public int[] getExitPoint() {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][grid[row].length - 1] == ' ') return new int[] {row, grid[row].length - 1}; // Exit on the right
        }
        throw new IllegalArgumentException("No exit point found on the right border.");
    }
}
