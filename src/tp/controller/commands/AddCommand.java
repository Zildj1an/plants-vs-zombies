package tp.controller.commands;

import tp.exceptions.CommandExecuteException;
import tp.exceptions.CommandParserException;
import tp.logic.Game;
import tp.logic.factories.PlantFactory;
import tp.logic.objects.Plant;

public class AddCommand extends Command{
    public static final String COMMAND_TEXT = "add";
    public static final String COMMAND_TEXT_MSG = "[A]dd";
    public static final String HELP_TEXT_MSG = "add flower";

    private static final String USAGE = COMMAND_TEXT + " <plant> <x> <y>";

    private String plant;
    private int x;
    private int y;

    public AddCommand() {
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }


    @Override
    public boolean execute(Game game) throws CommandExecuteException {
        Plant p = PlantFactory.getPlant(plant);

        try{
            game.addPlant(p, x, y);
        }catch (Exception e){
            throw new CommandExecuteException(e.getMessage());
        }finally {
            game.update();
        }

        return true;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParserException {
        Command c = null;

        if(commandWords[0].equals(COMMAND_TEXT) || commandWords[0].equals(Character.toString(COMMAND_TEXT.charAt(0)))) {
            if (commandWords.length != 4)
                throw new CommandParserException("Incorrect number of arguments for add command: " + USAGE);

            c = this;
            this.plant = commandWords[1];
            try {
                this.x = Integer.parseInt(commandWords[2]);
                this.y = Integer.parseInt(commandWords[3]);
            } catch (NumberFormatException e) {
                throw new CommandParserException("Invalid argument for add command, number expected: " + USAGE);
            }
        }
        return c;
    }
}
