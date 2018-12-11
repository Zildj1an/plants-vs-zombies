package tp.controller.commands;

import tp.logic.Game;

public class ExitCommand extends NoParamsCommand {
    public static final String COMMAND_TEXT = "exit";
    public static final String COMMAND_TEXT_MSG = "[E]xit";
    public static final String HELP_TEXT_MSG = "terminate the program.";

    public ExitCommand(){
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game){
        game.exit();

        return false;
    }
}