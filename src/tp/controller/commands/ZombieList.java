package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;
import tp.logic.factories.ZombieFactory;

public class ZombieList extends NoParamsCommand{
    public static final String commandText = "zombielist";
    public static final String commandTextMsg = "[Z]ombieList";
    public static final String helpTextMsg = "print the list of available zombies.";

    public ZombieList() {
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        System.out.println(ZombieFactory.listOfAvilableZombies());
        controller.setNoPrintGameState();
    }
}
