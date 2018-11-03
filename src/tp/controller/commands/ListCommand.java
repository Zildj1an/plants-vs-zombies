package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;
import tp.logic.factories.PlantFactory;

public class ListCommand extends NoParamsCommand {
    public static final String commandText = "list";
    public static final String commandTextMsg = "[L]ist";
    public static final String helpTextMsg = "print the list of available plants.";

    public ListCommand() {
        super(commandText, commandTextMsg, helpTextMsg);
    }

    public void execute(Game game, Controller controller) {
        System.out.println(PlantFactory.listOfAvilablePlants());
        controller.setNoPrintGameState();
    }



}
