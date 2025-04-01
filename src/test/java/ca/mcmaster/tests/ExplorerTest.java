package ca.mcmaster.tests;

import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class ExplorerTest {

    private char[][] grid;
    private Explorer explorer;

    @BeforeEach
    public void setUp() {
        grid = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        explorer = new Explorer(1, 0, grid); // Starts at (1,0) facing RIGHT
    }

    // 1. Test moving forward one tile
    @Test
    public void testMoveForward() {
        explorer.moveForward();
        assertEquals(1, explorer.getX());
        assertEquals(1, explorer.getY());
    }

    // 2. Test that right-hand rule starts with a valid move
    @Test
    public void testTurnRight() {
        explorer.moveRightHandRule();
        String path = explorer.getCanonicalPath();
        assertFalse(path.isEmpty());
        char firstMove = path.charAt(0);
        assertTrue(firstMove == 'R' || firstMove == 'F' || firstMove == 'L');
    }

    // 3. Test path compression for 3 forward moves
    @Test
    public void testGetCanonicalPath() {
        explorer.moveForward();
        explorer.moveForward();
        explorer.moveForward();
        assertEquals("3F", explorer.getCanonicalPath());
    }

    // 4. Test reaching the exit on a straight path
    @Test
    public void testHasReachedExitTrue() {
        Explorer temp = new Explorer(0, 0, new char[][]{{' ', ' ', ' '}});
        while (!temp.hasReachedExit()) {
            temp.moveForward();
        }
        assertTrue(temp.hasReachedExit());
    }

    // 5. Test that left/right turns get recorded in the path
    @Test
    public void testTurnLeft() {
        explorer.moveRightHandRule();
        explorer.moveRightHandRule();
        String path = explorer.getCanonicalPath();
        assertTrue(path.contains("L") || path.contains("R"));
    }

    // 6. Test that an empty maze throws an exception
    @Test
    public void testEmptyMazeThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Explorer(0, 0, new char[][]{}));
    }

    // 7. Test that starting on a wall throws an exception
    @Test
    public void testStartOnWall() {
        char[][] maze = {
            {'X', 'X'},
            {' ', ' '}
        };
        assertThrows(IllegalArgumentException.class, () -> new Explorer(0, 0, maze));
    }

    // 8. Test handling of a large maze (1000x1000) with open space
    @Test
    public void testLargeOpenMaze() {
        int size = 1000;
        char[][] bigMaze = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bigMaze[i][j] = ' ';
            }
        }
        Explorer bigExplorer = new Explorer(size / 2, 0, bigMaze);
        String path = bigExplorer.computePath();
        assertTrue(path.length() > 0);
    }

    // 9. Test compression of a real zig-zag maze path
    @Test
    public void testCanonicalPathCompressionWithTurns() {
        char[][] customMaze = {
            {'X', 'X', 'X', 'X', 'X'},
            {' ', ' ', ' ', ' ', 'X'},
            {'X', 'X', 'X', ' ', 'X'},
            {'X', 'X', 'X', ' ', 'X'},
            {'X', 'X', 'X', ' ', ' '},
            {'X', 'X', 'X', 'X', 'X'}
        };
        Explorer e = new Explorer(1, 0, customMaze);
        e.computePath();
        String compressed = e.getCanonicalPath();
        assertEquals("3FR3FLF", compressed);
    }

    // 10. Test that a maze with entry but no exit throws an error
    @Test
    public void testMazeThrowsWithoutExit() throws IOException {
        String mazeData =
            "XXXXX\n" +
            "    X\n" +
            "XXX X\n" +
            "XXX X\n" +
            "XXX X\n" +
            "XXXXX\n";
        BufferedReader reader = new BufferedReader(new StringReader(mazeData));
        Maze maze = new Maze(reader);
        assertThrows(IllegalArgumentException.class, maze::getExitPoint);
    }

    // 11. Test zig-zag pattern maze forcing multiple turns but solvable
    @Test
    public void testZigZagMaze() {
        char[][] maze = {
            {'X', 'X', 'X', 'X', 'X', 'X'},
            {' ', ' ', 'X', ' ', ' ', 'X'},
            {'X', ' ', 'X', ' ', 'X', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' '},
            {'X', 'X', 'X', 'X', 'X', 'X'}
        };
        Explorer e = new Explorer(1, 0, maze);
        String path = e.computePath();
        assertNotNull(path);
        assertEquals("FR2FL4F", path);
    }

    // 12. check if it reaches a dead end half way that it returns safely and notifies

    @Test
    public void testMazeWithBlockedExit() {
        char[][] maze = {
            {'X', 'X', 'X'},
            {' ', 'X', ' '},
            {'X', 'X', 'X'}
        };
        Explorer e = new Explorer(1, 0, maze);
        Exception exception = assertThrows(IllegalStateException.class, e::computePath);
        assertEquals("Maze runner returned to starting position — possible infinite loop.", exception.getMessage());
    }

    

    // 13. Maze with only one path but a hard turn
    @Test
    public void testSinglePathHardTurn() {
        char[][] maze = {
            {'X', 'X', 'X', 'X'},
            {' ', ' ', ' ', 'X'},
            {'X', 'X', ' ', 'X'},
            {'X', 'X', ' ', ' '},
            {'X', 'X', 'X', 'X'}
        };
        Explorer e = new Explorer(1, 0, maze);
        e.computePath();
        String path = e.getCanonicalPath();
        assertTrue(path.contains("FRF") || path.contains("FLF"));
    }

    // 14. Test long straight corridor
    @Test
    public void testLongStraightCorridor() {
        char[][] maze = new char[1][500];
        for (int i = 0; i < 500; i++) {
            maze[0][i] = ' ';
        }
        Explorer e = new Explorer(0, 0, maze);
        String path = e.computePath();
        assertEquals("499F", path);
    }
}
