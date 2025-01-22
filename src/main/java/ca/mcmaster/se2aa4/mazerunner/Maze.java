package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Maze {
    private char[][] grid;

    public Maze(BufferedReader reader) throws IOException {
        // Read all lines and calculate the maximum row length
        var lines = reader.lines().toList();
        int maxLength = lines.stream().mapToInt(String::length).max().orElse(0);

        // Convert lines to a char[][], filling blank lines with spaces
        grid = lines.stream()
                .map(line -> line.isBlank() ? " ".repeat(maxLength).toCharArray() : line.toCharArray())
                .toArray(char[][]::new);

        if (grid.length == 0 || maxLength == 0) {
            throw new IllegalArgumentException("Maze is empty or invalid.");
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public int[] getEntryPoint() {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][0] == ' ') {
                return new int[]{row, 0};
            }
        }
        throw new IllegalArgumentException("No entry point found.");
    }

    public int[] getExitPoint() {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][grid[row].length - 1] == ' ') {
                return new int[]{row, grid[row].length - 1};
            }
        }
        throw new IllegalArgumentException("No exit point found.");
    }
}
