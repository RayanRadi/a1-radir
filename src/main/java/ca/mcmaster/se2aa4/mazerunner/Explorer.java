package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.commands.*;
import java.util.ArrayList;
import java.util.List;

public class Explorer {

    private int x, y;
    private final int startX, startY;
    private char[][] grid;
    private List<String> path;
    private String direction;
    private int exitCol;

    public Explorer(int startX, int startY, char[][] maze) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            throw new IllegalArgumentException("Maze grid cannot be empty");
        }
        if (maze[startX][startY] != ' ') {
            throw new IllegalArgumentException("Starting point must be a free space");
        }

        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;
        this.grid = maze;
        this.path = new ArrayList<>();

        if (startY == 0) {
            this.direction = "RIGHT";
            this.exitCol = grid[0].length - 1;
        } else if (startY == grid[0].length - 1) {
            this.direction = "LEFT";
            this.exitCol = 0;
        } else {
            this.direction = "RIGHT";
            this.exitCol = grid[0].length - 1;
        }
    }

    public String computePath() {
        int maxSteps = 5000;
        while (!hasReachedExit() && maxSteps-- > 0) {
            moveRightHandRule();
        }
        if (!hasReachedExit()) {
            throw new IllegalStateException("Failed to reach the exit within allowed steps.");
        }
        return getCanonicalPath();
    }

    public void moveRightHandRule() {
        if (x == startX && y == startY && !path.isEmpty()) {
            throw new IllegalStateException("Maze runner returned to starting position — possible infinite loop.");
        }

        if (canMoveForward() && !canMoveRight() && !canMoveLeft()) {
            new MoveForwardCommand(this).execute();
        } else if (canMoveRight()) {
            new TurnRightCommand(this).execute();
            new MoveForwardCommand(this).execute();
        } else if (canMoveForward()) {
            new MoveForwardCommand(this).execute();
        } else if (canMoveLeft()) {
            new TurnLeftCommand(this).execute();
            new MoveForwardCommand(this).execute();
        } else {
            new TurnRightCommand(this).execute();
            new TurnRightCommand(this).execute();  
        }
    }

    public boolean hasReachedExit() {
        return y == exitCol;
    }

    public boolean canMoveRight() {
        if (direction.equals("UP")) {
            return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
        } else if (direction.equals("DOWN")) {
            return y - 1 >= 0 && grid[x][y - 1] == ' ';
        } else if (direction.equals("LEFT")) {
            return x - 1 >= 0 && grid[x - 1][y] == ' ';
        } else { // RIGHT
            return x + 1 < grid.length && grid[x + 1][y] == ' ';
        }
    }

    public boolean canMoveForward() {
        if (direction.equals("UP")) {
            return x - 1 >= 0 && grid[x - 1][y] == ' ';
        } else if (direction.equals("DOWN")) {
            return x + 1 < grid.length && grid[x + 1][y] == ' ';
        } else if (direction.equals("LEFT")) {
            return y - 1 >= 0 && grid[x][y - 1] == ' ';
        } else { // RIGHT
            return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
        }
    }

    public boolean canMoveLeft() {
        if (direction.equals("UP")) {
            return y - 1 >= 0 && grid[x][y - 1] == ' ';
        } else if (direction.equals("DOWN")) {
            return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
        } else if (direction.equals("LEFT")) {
            return x + 1 < grid.length && grid[x + 1][y] == ' ';
        } else { // RIGHT
            return x - 1 >= 0 && grid[x - 1][y] == ' ';
        }
    }

    public void doMoveForward() {
        if (direction.equals("UP")) {
            x--;
        } else if (direction.equals("DOWN")) {
            x++;
        } else if (direction.equals("LEFT")) {
            y--;
        } else { // RIGHT
            y++;
        }
        path.add("F");
    }

    public void doTurnRight() {
        if (direction.equals("UP")) {
            direction = "RIGHT";
        } else if (direction.equals("RIGHT")) {
            direction = "DOWN";
        } else if (direction.equals("DOWN")) {
            direction = "LEFT";
        } else { // LEFT
            direction = "UP";
        }
        path.add("R");
    }

    public void doTurnLeft() {
        if (direction.equals("UP")) {
            direction = "LEFT";
        } else if (direction.equals("LEFT")) {
            direction = "DOWN";
        } else if (direction.equals("DOWN")) {
            direction = "RIGHT";
        } else { // RIGHT
            direction = "UP";
        }
        path.add("L");
    }

    public String getCanonicalPath() {
        if (path.isEmpty()) return "";
        StringBuilder compressedPath = new StringBuilder();
        String prevMove = path.get(0);
        int count = 1;
        for (int i = 1; i < path.size(); i++) {
            if (path.get(i).equals(prevMove)) {
                count++;
            } else {
                if (count > 1) {
                    compressedPath.append(count);
                }
                compressedPath.append(prevMove);
                prevMove = path.get(i);
                count = 1;
            }
        }
        if (count > 1) {
            compressedPath.append(count);
        }
        compressedPath.append(prevMove);
        return compressedPath.toString();
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
