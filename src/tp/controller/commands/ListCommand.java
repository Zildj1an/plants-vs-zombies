package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class ListCommand extends NoParamsCommand {
    public static final String CommandText = "list";
    public static final String CommandTextMsg = "[L]ist";
    public static final String HelpTextMsg = "print the list of available plants.";

    public ListCommand() {
        super(CommandText, CommandTextMsg, HelpTextMsg);
    }

    public void execute(Game game, Controller controller) {
        for(String s: game.listPlantsAvailable())
            System.out.println(s);
        controller.setNoPrintGameState();
    }



}
