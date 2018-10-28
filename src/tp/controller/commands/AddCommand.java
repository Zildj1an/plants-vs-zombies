package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class AddCommand extends Command{
    public static final String commandText = "add";
    public static final String commandTextMsg = "[A]dd";
    public static final String helpTextMsg = "add flower";

    private String plant;
    private int x;
    private int y;

    public AddCommand() {
        super(commandText, commandTextMsg, helpTextMsg);
    }


    @Override
    public void execute(Game game, Controller controller) {
        switch (plant){
            case "peashooter":
            case "p":
                game.addPeashooter(x,y);
                break;
            case "sunflower":
            case "s":
                game.addSunflower(x,y);
                break;
        }
    }

    @Override
    public Command parse(String[] commandWords, Controller controller) {
        Command c = null;

        if(commandWords.length == 4 && (commandWords[0].equals(commandText) || commandWords[0].equals(Character.toString(commandText.charAt(0))))) {
            c = this;
            this.plant = commandWords[1];
            this.x = Integer.parseInt(commandWords[2]);
            this.y = Integer.parseInt(commandWords[3]);
        }
        return c;
    }
}
