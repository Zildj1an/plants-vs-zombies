package tp.controller.commands;

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
    public boolean execute(Game game) {
        System.out.println(ZombieFactory.listOfAvilableZombies());
        return false;
    }
}
