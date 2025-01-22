package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Explorer {
    private int x, y; // Current position
    private List<String> path;
    private char[][] maze;

    public Explorer(int startX, int startY, char[][] maze) {
        this.x = startX;
        this.y = startY;
        this.maze = maze;
        this.path = new ArrayList<>();
    }

    public void moveForward() {
        if (y + 1 < maze[x].length && maze[x][y + 1] == ' ') { // Check if forward is clear
            y++;
            path.add("F");
        }
    }

    public String getCanonicalPath() {
        return path.size() + "F";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
