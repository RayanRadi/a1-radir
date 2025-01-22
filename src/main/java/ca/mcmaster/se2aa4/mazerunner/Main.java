package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String inputFile = cmd.getOptionValue("i");
                logger.info("Reading the maze from file: " + inputFile);

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    Maze maze = new Maze(reader);
                    int[] entry = maze.getEntryPoint();
                    int[] exit = maze.getExitPoint();

                    Explorer explorer = new Explorer(entry[0], entry[1], maze.getGrid());

                    while (explorer.getY() < exit[1]) {
                        explorer.moveForward();
                    }

                    logger.info("Factorized Path: " + explorer.getCanonicalPath());
                } catch (Exception e) {
                    logger.error("An error occurred while reading the maze file", e);
                }
            } else {
                logger.error("Input file not specified. Use -i <file>");
            }
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments.", e);
        }

        logger.info("** End of Maze Runner");
    }
}
