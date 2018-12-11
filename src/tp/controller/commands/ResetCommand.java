package tp.controller.commands;

import tp.logic.Game;

public class ResetCommand extends NoParamsCommand {
    public static final String COMMAND_TEXT = "reset";
    public static final String COMMAND_TEXT_MSG = "[R]eset";
    public static final String HELP_TEXT_MSG = "reset game";

    public ResetCommand(){
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game) {
        game.reset();
        return true;
    }
}
