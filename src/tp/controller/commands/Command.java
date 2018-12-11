package tp.controller.commands;

import tp.exceptions.CommandExecuteException;
import tp.exceptions.CommandParserException;
import tp.logic.Game;

public abstract class Command {

    private String helpText;
    private String commandText;
    protected final String commandName;

    public Command(String commandText, String commandInfo, String helpInfo){
        this.commandText = commandInfo;
        helpText = helpInfo;
        String[] commandInfoWords = commandText.split("\\s+");
        commandName = commandInfoWords[0];
    }

    public abstract boolean execute(Game game) throws CommandExecuteException;
    public abstract Command parse(String[] commandWords) throws CommandParserException;
    public String helpText(){
        return " " + commandText + ": " + helpText;
    }
}
