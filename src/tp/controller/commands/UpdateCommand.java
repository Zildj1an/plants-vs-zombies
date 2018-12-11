package tp.controller.commands;

import tp.logic.Game;

public class UpdateCommand extends NoParamsCommand {
    public static final String COMMAND_TEXT = "";
    public static final String COMMAND_TEXT_MSG = "[N]one";
    public static final String HELP_TEXT_MSG = "skips cycle.";

    public UpdateCommand() {
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game) {
        game.update();
        return true;
    }
}
