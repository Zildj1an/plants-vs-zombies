package tp.controller.commands;

import tp.exceptions.CommandParserException;

public class CommandParser {
    private static Command[] availableCommands = { new AddCommand(), new HelpCommand(),
            new ResetCommand(), new ExitCommand(), new ListCommand(),new UpdateCommand(),
            new PrintMode(), new SaveCommand(), new LoadCommand(), new ZombieList(), new AddZombie()};

    public static Command parseCommand(String[] commandWords) throws CommandParserException {

        for (Command available: availableCommands) {
            if (available.parse(commandWords) != null)
                return available;

        }
        return null;
    }

    public static String commandHelp() {
        StringBuilder text = new StringBuilder();

        for (Command c : availableCommands) {
            text.append(c.helpText()).append("\n");
        }

        return text.toString();
    }
}
