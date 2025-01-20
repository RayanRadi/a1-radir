package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the path taken by the explorer.
 */
public class Path {
    private List<String> steps; // Sequence of steps ("F", "R", "L")

    /**
     * Constructor for Path
     */
    public Path() {
        steps = new ArrayList<>();
    }

    /**
     * Adds a step to the path
     * param step A single step ("F", "R", or "L")
     */
    public void addStep(String step) {
        // TO DO: Validate input (ensure it's "F", "R", or "L") before adding.
        steps.add(step);
    }

    /**
     * Returns the canonical representation of the path 
     */
    public String getCanonicalPath() {
        // TO DO: Combine  the list of steps into a single string.
        return String.join("", steps);
    }

    /**
     * Returns the factorized representation of the path (e.g., "3F1R2L").
     * TO DO:  Implement factorization logic  for repeated steps.
     */
    public String getFactorizedPath() {
        // TO DO:  Implement  factorized path generation.
        return null;
    }
}
