/*
 * Main.java: This is the main entry point of the program.
 * It parses command line arguments, reads the maze file, and either computes or verifies the maze path.
 *
 * Rayan Radi - 400503807
 */

 package ca.mcmaster.se2aa4.mazerunner;

 import java.io.BufferedReader;
 import java.io.FileReader;
 import org.apache.commons.cli.*;
 
 public class Main {
     public static void main(String[] args) {
         Options options = new Options();
         options.addOption("i", "input", true, "Input maze file");
         options.addOption("p", "path", true, "Path sequence to verify");
 
         CommandLineParser parser = new DefaultParser();
         CommandLine cmd;
         try {
             cmd = parser.parse(options, args);
         } catch (ParseException e) {
             System.err.println("Error parsing command-line arguments.");
             return;
         }
         if (cmd.hasOption("i")) {
             String inputFile = cmd.getOptionValue("i");
             try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                 Maze maze = new Maze(reader);
                 int[] entry = maze.getEntryPoint();
                 Explorer explorer = new Explorer(entry[0], entry[1], maze.getGrid());
                 String computedPath = explorer.computePath();
                 if (cmd.hasOption("p")) {
                     String userPath = cmd.getOptionValue("p");
                     System.out.println(computedPath.equals(userPath) ? "correct path" : "incorrect path");
                 } else {
                     System.out.println(computedPath);
                 }
             } catch (Exception e) {
                 System.err.println("An error occurred: " + e.getMessage());
             }
         } else {
             System.err.println("Input file not specified. Use -i <file>");
         }
     }
 }
 