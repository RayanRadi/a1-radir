/*
 * Maze.java: This file reads the maze from a text file and converts it into a 2D char array.
 * It also finds the entry and exit points on the maze borders.
 *
 * Rayan Radi - 400503807
 */

 package ca.mcmaster.se2aa4.mazerunner;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 public class Maze {
     private char[][] grid;
 
     public Maze(BufferedReader reader) throws IOException {
         List<String> lineList = new ArrayList<>();
         String line;
         while ((line = reader.readLine()) != null) {
             lineList.add(line);
         }
         // Determine the maximum line length and pad lines to that width.
         int maxWidth = 0;
         for (String l : lineList) {
             if (l.length() > maxWidth) {
                 maxWidth = l.length();
             }
         }
         for (int i = 0; i < lineList.size(); i++) {
             String l = lineList.get(i);
             if (l.length() < maxWidth) {
                 StringBuilder sb = new StringBuilder(l);
                 while (sb.length() < maxWidth) {
                     sb.append(" ");
                 }
                 lineList.set(i, sb.toString());
             }
         }
         grid = new char[lineList.size()][];
         for (int i = 0; i < lineList.size(); i++) {
             grid[i] = lineList.get(i).toCharArray();
         }
     }
 
     public char[][] getGrid() {
         return grid;
     }
 
     public int[] getEntryPoint() {
         if (grid.length == 0 || grid[0].length == 0) {
             throw new IllegalArgumentException("Maze grid is empty.");
         }
     
         // Look for an entry on the left border (a whitespace cell).
         for (int row = 0; row < grid.length; row++) {
             if (Character.isWhitespace(grid[row][0])) {
                 return new int[]{row, 0};
             }
         }
         // If not found on the left, look for an entry on the right border.
         for (int row = 0; row < grid.length; row++) {
             if (Character.isWhitespace(grid[row][grid[row].length - 1])) {
                 return new int[]{row, grid[row].length - 1};
             }
         }
         throw new IllegalArgumentException("No entry point found on the maze borders.");
     }
 
     public int[] getExitPoint() {
         int[] entry = getEntryPoint();
         if (entry[1] == 0) {
             // Entry on left, exit on right.
             for (int row = 0; row < grid.length; row++) {
                 if (Character.isWhitespace(grid[row][grid[row].length - 1])) {
                     return new int[]{row, grid[row].length - 1};
                 }
             }
         } else if (entry[1] == grid[0].length - 1) {
             // Entry on right, exit on left.
             for (int row = 0; row < grid.length; row++) {
                 if (Character.isWhitespace(grid[row][0])) {
                     return new int[]{row, 0};
                 }
             }
         }
         throw new IllegalArgumentException("No exit point found on the maze borders.");
     }
 }
 