/*
 * Explorer.java: This file contains the logic for navigating the maze.
 * It uses a right-hand rule (with a special case for straight corridors) to compute the path.
 * The computed path is then factorized (e.g., "4F" for 4 forward moves).
 *
 * Rayan Radi - 400503807
 */

 package ca.mcmaster.se2aa4.mazerunner;

 import java.util.ArrayList;
 import java.util.List;
 
 public class Explorer {
     private int x, y;
     private char[][] grid;
     private List<String> path;
     private String direction;
     private int exitCol;
 
     public Explorer(int startX, int startY, char[][] maze) {
         this.x = startX;
         this.y = startY;
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
         int maxSteps = 500;
         while (!hasReachedExit() && maxSteps-- > 0) {
             moveRightHandRule();
         }
         return getCanonicalPath();
     }
 
     // If forward is available and neither right nor left is available, move forward.
     public void moveRightHandRule() {
         if (canMoveForward() && !canMoveRight() && !canMoveLeft()) {
             moveForward();
         } else if (canMoveRight()) {
             turnRight();
             moveForward();
         } else if (canMoveForward()) {
             moveForward();
         } else if (canMoveLeft()) {
             turnLeft();
             moveForward();
         } else {
             turnBack();
         }
     }
 
     public boolean hasReachedExit() {
         return y == exitCol;
     }
 
     public boolean canMoveRight() {
         switch (direction) {
             case "UP":    return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
             case "DOWN":  return y - 1 >= 0 && grid[x][y - 1] == ' ';
             case "LEFT":  return x - 1 >= 0 && grid[x - 1][y] == ' ';
             case "RIGHT": return x + 1 < grid.length && grid[x + 1][y] == ' ';
             default:      return false;
         }
     }
 
     public boolean canMoveForward() {
         switch (direction) {
             case "UP":    return x - 1 >= 0 && grid[x - 1][y] == ' ';
             case "DOWN":  return x + 1 < grid.length && grid[x + 1][y] == ' ';
             case "LEFT":  return y - 1 >= 0 && grid[x][y - 1] == ' ';
             case "RIGHT": return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
             default:      return false;
         }
     }
 
     public boolean canMoveLeft() {
         switch (direction) {
             case "UP":    return y - 1 >= 0 && grid[x][y - 1] == ' ';
             case "DOWN":  return y + 1 < grid[0].length && grid[x][y + 1] == ' ';
             case "LEFT":  return x + 1 < grid.length && grid[x + 1][y] == ' ';
             case "RIGHT": return x - 1 >= 0 && grid[x - 1][y] == ' ';
             default:      return false;
         }
     }
 
     public void moveForward() {
         switch (direction) {
             case "UP":    x--; break;
             case "DOWN":  x++; break;
             case "LEFT":  y--; break;
             case "RIGHT": y++; break;
         }
         path.add("F");
     }
 
     private void turnRight() {
         switch (direction) {
             case "UP":    direction = "RIGHT"; break;
             case "RIGHT": direction = "DOWN"; break;
             case "DOWN":  direction = "LEFT"; break;
             case "LEFT":  direction = "UP"; break;
         }
         path.add("R");
     }
 
     private void turnLeft() {
         switch (direction) {
             case "UP":    direction = "LEFT"; break;
             case "LEFT":  direction = "DOWN"; break;
             case "DOWN":  direction = "RIGHT"; break;
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
 
     public int getX() {
         return x;
     }
 
     public int getY() {
         return y;
     }
 }
 