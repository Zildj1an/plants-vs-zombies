package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class AddZombie extends Command {
    public static final String commandText = "addzombie";
    public static final String commandTextMsg = "[A]dd";
    public static final String helpTextMsg = "add zombie [only debug purpose]";

    private String zombie;
    private int x;
    private int y;

    public AddZombie(){
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        if(!game.addZombie(zombie, x, y)){
            System.out.println("[ERROR] zombie " + zombie + " couldn't be added");
        }
    }

    @Override
    public Command parse(String[] commandWords, Controller controller) {
        Command c = null;

        if(commandWords.length == 4 && (commandWords[0].equals(commandText))) {
            c = this;
            this.zombie = commandWords[1];
            this.x = Integer.parseInt(commandWords[2]);
            this.y = Integer.parseInt(commandWords[3]);
        }
        return c;
    }
}
