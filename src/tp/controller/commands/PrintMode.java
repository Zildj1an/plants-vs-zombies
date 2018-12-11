package tp.controller.commands;

import tp.exceptions.CommandExecuteException;
import tp.exceptions.CommandParserException;
import tp.logic.Game;
import tp.logic.printers.DebugPrinter;
import tp.logic.printers.ReleasePrinter;

public class PrintMode extends Command {

    public static final String COMMAND_TEXT = "printmode";
    public static final String COMMAND_TEXT_MSG = "[P]rintMode";
    public static final String HELP_TEXT_MSG = "change print mode [Release|Debug].";

    private static final String USAGE = COMMAND_TEXT_MSG + " release|debug";

    private String mode;

    public PrintMode(){
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException {
        switch (mode){
            case "debug":
                game.setModePrint(new DebugPrinter());
                break;
            case "release":
                game.setModePrint(new ReleasePrinter());
                break;
            default:
                throw  new CommandExecuteException("Unknown print mode: " + mode);
        }

        return true;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParserException {
        Command c = null;

        if (commandWords[0].equals(COMMAND_TEXT) || commandWords[0].equals(Character.toString(COMMAND_TEXT.charAt(0)))){
            if(commandWords.length != 2) throw new CommandParserException("Incorrect number of arguments for printmode command: " + USAGE);
            c = this;
            this.mode = commandWords[1];
        }

        return c;
    }
}
