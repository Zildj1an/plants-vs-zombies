package tp.controller.commands;

import tp.logic.Game;


public class HelpCommand extends NoParamsCommand {
    public static final String COMMAND_TEXT = "help";
    public static final String COMMAND_TEXT_MSG = "[H]elp";
    public static final String HELP_TEXT_MSG = "print this help message.";


    public HelpCommand() {
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game) {
        System.out.println("The available commands are:  \n" + CommandParser.commandHelp());

        return false;
    }

}