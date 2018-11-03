package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;


public class HelpCommand extends NoParamsCommand {
    public static final String commandText = "help";
    public static final String commandTextMsg = "[H]elp";
    public static final String helpTextMsg = "print this help message.";


    public HelpCommand() {
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        System.out.println("The available commands are:  \n" + CommandParser.commandHelp());
        controller.setNoPrintGameState();
    }

}