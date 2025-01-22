package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Explorer {
    private int x, y; // Current position
    private List<String> path;

    public Explorer(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.path = new ArrayList<>();
    }

    public void moveForward() {
        y++; // Move right
        path.add("F");
    }

    public String getCanonicalPath() {
        int forwardCount = path.size();
        return forwardCount + "F"; // Factorized path
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
