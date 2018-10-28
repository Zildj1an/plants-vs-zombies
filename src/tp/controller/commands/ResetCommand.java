package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class ResetCommand extends NoParamsCommand {
    public static final String CommandText = "reset";
    public static final String CommandTextMsg = "[R]eset";
    public static final String HelpTextMsg = "reset game";

    public ResetCommand(){
        super(CommandText, CommandTextMsg, HelpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        game.reset();
    }
}
