package ca.mcmaster.se2aa4.mazerunner;

/**
 * represents the main character  navigating the maze.
 */
public class Explorer {
    private int x, y; // current position in the maze
    private Direction direction; // current  facing direction

    /**
     * Constructor for Explorer.
     * param startX starting x -cordinate.
     * param startY starting y- coordinate.
     * param startDirection initial direction the explorer is facing.
     */
    public Explorer(int startX, int startY, Direction startDirection) {
        this.x = startX;
        this.y = startY;
        this.direction = startDirection;
    }

    /**
     * moves the explorer forward in the current direction.
     * next steps: Updae (x, y) position  based on the direction.
     */
    public void moveForward() {
        // TODO: Implement movement logic.
    }

    /**
     * Turns the explorer to the left (counterclockwise).
     * TODO: Update the direction to the left.
     */
    public void turnLeft() {
        // TO DO: Implement direction change for turning left.
    }

    /**
     * Turns the explorer to the right (clockwise).
     * TO DO: update the direction to the right.
     */
    public void turnRight() {
        // TODO: Implement  direction change for turning right.
    }
}
