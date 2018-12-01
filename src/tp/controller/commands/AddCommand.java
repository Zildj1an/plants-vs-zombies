package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;
import tp.logic.factories.PlantFactory;
import tp.logic.objects.Plant;

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
        Plant p = PlantFactory.getPlant(plant);
        if (!game.addPlant(p, x, y)){
            System.out.println("[ERROR] plant " + plant + " couldn't be added");
        }
        game.update();
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
