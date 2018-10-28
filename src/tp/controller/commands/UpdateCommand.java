package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class UpdateCommand extends NoParamsCommand {
    public static final String CommandText = "";
    public static final String CommandTextMsg = "none";
    public static final String HelpTextMsg = "skips cycle.";

    public UpdateCommand() {
        super(CommandText, CommandTextMsg, HelpTextMsg);
    }

    public void execute(Game game, Controller controller) {
        game.update();
    }
}
