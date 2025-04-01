package ca.mcmaster.se2aa4.mazerunner;


public class ExplorerFactory {


    public static Explorer createRightHandRuleExplorer(Maze maze) {
        int[] entry = maze.getEntryPoint();
        return new Explorer(entry[0], entry[1], maze.getGrid());
    }


    // Later on, a user can make more explorer's here if needed.
    //Such as optimal explorer or random explorer or longest path explorer.
}
