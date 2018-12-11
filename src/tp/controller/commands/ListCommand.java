package tp.controller.commands;

import tp.logic.Game;
import tp.logic.factories.PlantFactory;

public class ListCommand extends NoParamsCommand {
    public static final String COMMAND_TEXT = "list";
    public static final String COMMAND_TEXT_MSG = "[L]ist";
    public static final String HELP_TEXT_MSG = "print the list of available plants.";

    public ListCommand() {
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    public boolean execute(Game game) {
        System.out.println(PlantFactory.listOfAvilablePlants());

        return false;
    }



}
