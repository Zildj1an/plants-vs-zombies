package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;


public class HelpCommand extends NoParamsCommand {
    public static final String CommandText = "help";
    public static final String CommandTextMsg = "[H]elp";
    public static final String HelpTextMsg = "print this help message.";


    public HelpCommand() {
        super(CommandText, CommandTextMsg, HelpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        System.out.println("The available commands are:  \n" + CommandParser.commandHelp());
        controller.setNoPrintGameState();
    }

}