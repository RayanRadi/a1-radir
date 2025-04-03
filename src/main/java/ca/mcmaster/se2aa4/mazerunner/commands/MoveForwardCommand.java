package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class MoveForwardCommand implements Command {
    private final Explorer explorer;

    public MoveForwardCommand(Explorer explorer) {
        this.explorer = explorer;
    }

    @Override
    public void execute() {
        explorer.doMoveForward();
    }
}
