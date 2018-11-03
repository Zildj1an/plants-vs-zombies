package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class UpdateCommand extends NoParamsCommand {
    public static final String commandText = "";
    public static final String commandTextMsg = "none";
    public static final String helpTextMsg = "skips cycle.";

    public UpdateCommand() {
        super(commandText, commandTextMsg, helpTextMsg);
    }

    public void execute(Game game, Controller controller) {
        game.update();
    }
}
