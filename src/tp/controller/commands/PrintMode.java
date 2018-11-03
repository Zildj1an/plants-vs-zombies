package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class PrintMode extends NoParamsCommand {

    public static final String commandText = "printmode";
    public static final String commandTextMsg = "[P]rintMode";
    public static final String helpTextMsg = "change print mode [Release|Debug].";

    public PrintMode(){
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        controller.switchPrintMode();
        controller.setNoPrintGameState();
    }
}
