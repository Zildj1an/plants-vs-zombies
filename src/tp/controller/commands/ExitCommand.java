package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class ExitCommand extends NoParamsCommand {
    public static final String commandText = "exit";
    public static final String commandTextMsg = "[E]xit";
    public static final String helpTextMsg = "terminate the program.";

    public ExitCommand(){
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        controller.setExit();
        controller.setNoPrintGameState();
    }
}