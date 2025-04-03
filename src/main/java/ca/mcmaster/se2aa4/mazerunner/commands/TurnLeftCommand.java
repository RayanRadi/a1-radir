package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class TurnLeftCommand implements Command {
    private final Explorer explorer;

    public TurnLeftCommand(Explorer explorer) {
        this.explorer = explorer;
    }

    @Override
    public void execute() {
        explorer.doTurnLeft();
    }
}
