package tp.controller.commands;

import tp.controller.Controller;

public abstract class NoParamsCommand extends Command {

    public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
        super(commandText, commandInfo, helpInfo);
    }

    @Override
    public Command parse(String[] commandWords, Controller controller) {

        if(commandName.equals(""))
            return (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName) ? this : null);
        else
            return (commandWords.length == 1 && (commandWords[0].equalsIgnoreCase(commandName) || commandWords[0].equals(Character.toString(commandName.charAt(0)))) ? this : null);
    }
}
