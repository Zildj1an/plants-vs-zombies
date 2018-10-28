package tp.controller.commands;

import tp.controller.Controller;
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

    public abstract void execute(Game game, Controller controller);
    public abstract Command parse(String[] commandWords, Controller controller);
    public String helpText(){
        return " " + commandText + ": " + helpText;
    }
}
