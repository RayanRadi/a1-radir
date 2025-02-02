package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Explorer {
    private int x, y; // Current position
    private char[][] grid; // The maze grid
    private List<String> path;
    private String direction; // UP, DOWN, LEFT, RIGHt

    public Explorer(int startX, int startY, char[][] maze) {
        this.x = startX;
        this.y = startY;
        this.grid = maze;
        this.path = new ArrayList<>();
        this.direction = "RIGHT"; // Default direction (facing right)
    }

    public void moveRightHandRule() {
        int maxSteps = 500; // Prevent infinite loops
        int steps = 0;

        while (!hasReachedExit() && steps < maxSteps) {
            if (canMoveRight()) {
                turnRight();
                moveForward();
            } else if (canMoveForward()) {
                moveForward();
            } else if (canMoveLeft()) {
                turnLeft();
                moveForward();
            } else {
                turnBack(); // Use two right turns
            }
            steps++;
        }
    }

    public boolean hasReachedExit() { 
        int exitCol = grid[0].length - 1; // Exit is in the last column
        return y == exitCol;
    }

    public boolean canMoveRight() {   // check if the player can move right if so do it 
        switch (direction) {
            case "UP": return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
            case "DOWN": return y - 1 >= 0 && grid[x][y - 1] == ' ';
            case "LEFT": return x - 1 >= 0 && grid[x - 1][y] == ' ';
            case "RIGHT": return x + 1 < grid.length && grid[x + 1][y] == ' ';
            default: return false;
        }
    }

    public boolean canMoveForward() {    //check if player can mvoe forward if so do it
        switch (direction) {
            case "UP": return x - 1 >= 0 && grid[x - 1][y] == ' ';
            case "DOWN": return x + 1 < grid.length && grid[x + 1][y] == ' ';
            case "LEFT": return y - 1 >= 0 && grid[x][y - 1] == ' ';
            case "RIGHT": return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
            default: return false;
        }
    }

    public boolean canMoveLeft() {
        switch (direction) {
            case "UP": return y - 1 >= 0 && grid[x][y - 1] == ' ';
            case "DOWN": return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
            case "LEFT": return x + 1 < grid.length && grid[x + 1][y] == ' ';
            case "RIGHT": return x - 1 >= 0 && grid[x - 1][y] == ' ';
            default: return false;
        }
    }

    public void moveForward() {
        switch (direction) {
            case "UP": x--; break;
            case "DOWN": x++; break;
            case "LEFT": y--; break;
            case "RIGHT": y++; break;
        }
        path.add("F");
    }

    private void turnRight() {
        switch (direction) {
            case "UP": direction = "RIGHT"; break;
            case "RIGHT": direction = "DOWN"; break;
            case "DOWN": direction = "LEFT"; break;
            case "LEFT": direction = "UP"; break;
        }
        path.add("R");
    }

    private void turnLeft() {
        switch (direction) {
            case "UP": direction = "LEFT"; break;
            case "LEFT": direction = "DOWN"; break;
            case "DOWN": direction = "RIGHT"; break;
            case "RIGHT": direction = "UP"; break;
        }
        path.add("L");
    }

    private void turnBack() { 
        turnRight();
        turnRight();
    }

    public String getCanonicalPath() {
        if (path.isEmpty()) return "";

        StringBuilder compressedPath = new StringBuilder();
        String prevMove = path.get(0);
        int count = 1;

        for (int i = 1; i < path.size(); i++) {
            if (path.get(i).equals(prevMove)) {
                count++; // Increment count for consecutive moves
            } else {
                if (count > 1) {
                    compressedPath.append(count); // Add factorized count
                }
                compressedPath.append(prevMove); // Add move type
                prevMove = path.get(i);
                count = 1; // Reset count
            }
        }

        // Append the last move
        if (count > 1) {
            compressedPath.append(count);
        }
        compressedPath.append(prevMove);

        return compressedPath.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
