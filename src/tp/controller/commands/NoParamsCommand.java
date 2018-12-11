package tp.controller.commands;

import tp.exceptions.CommandParserException;

public abstract class NoParamsCommand extends Command {

    public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
        super(commandText, commandInfo, helpInfo);
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParserException {
        Command c = null;

        if(commandName.equals(""))
            c = commandWords[0].equalsIgnoreCase(commandName) ? this : null;
        else {
            if (commandWords[0].equalsIgnoreCase(commandName) || commandWords[0].equals(Character.toString(commandName.charAt(0)))) {
                if (commandWords.length != 1) throw new CommandParserException(commandName + " command has no argument");
                c = this;
            }
        }
        return c;
    }
}
