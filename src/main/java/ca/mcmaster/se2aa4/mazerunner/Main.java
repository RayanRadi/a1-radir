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

            // check if the input file flag is provided
            if (cmd.hasOption("i")) {
                String inputFile = cmd.getOptionValue("i");
                logger.info("Reading the maze from file: " + inputFile);

                // read and process the maze file
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (int idx = 0; idx < line.length(); idx++) {
                            if (line.charAt(idx) == '#') {
                                logger.debug("WALL ");
                            } else if (line.charAt(idx) == ' ') {
                                logger.debug("PASS ");
                            }
                        }
                        logger.debug(System.lineSeparator());
                    }
                } catch (Exception e) {
                    logger.error("An error occurred while reading the maze file", e);
                }
            } else {
                logger.error("Input file not specified. Use -i <file>");
            }
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments", e);
        }

        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of Maze Runner");
    }
}
