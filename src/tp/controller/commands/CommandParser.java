package tp.controller.commands;

import tp.controller.Controller;

public class CommandParser {
    private static Command[] availableCommands = { new AddCommand(), new HelpCommand(),
            new ExitCommand(), new ResetCommand(), new ListCommand(), new UpdateCommand()};

    public static Command parseCommand(String[] commandWords, Controller controller){

        for (Command available: availableCommands) {
            if (available.parse(commandWords, controller) != null)
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
