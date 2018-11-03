package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class ResetCommand extends NoParamsCommand {
    public static final String commandText = "reset";
    public static final String commandTextMsg = "[R]eset";
    public static final String helpTextMsg = "reset game";

    public ResetCommand(){
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        game.reset();
    }
}
