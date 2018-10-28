package tp.controller.commands;

import tp.controller.Controller;

public abstract class NoParamsCommand extends Command {

    public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
        super(commandText, commandInfo, helpInfo);
    }

    @Override
    public Command parse(String[] commandWords, Controller controller) {
        //TODO add single character command
        return (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName) ? this : null);
    }
}
