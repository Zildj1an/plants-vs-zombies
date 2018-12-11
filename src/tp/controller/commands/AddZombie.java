package tp.controller.commands;

import tp.exceptions.CommandExecuteException;
import tp.exceptions.CommandParserException;
import tp.logic.Game;
import tp.logic.factories.ZombieFactory;
import tp.logic.objects.Zombie;

public class AddZombie extends Command {
    public static final String COMMAND_TEXT = "addzombie";
    public static final String COMMAND_TEXT_MSG = "addZombie";
    public static final String HELP_TEXT_MSG = "add zombie [only debug purpose]";

    private static final String USAGE = "[A]ddZombie <zombie> <x> <y>";

    private String zombie;
    private int x;
    private int y;

    public AddZombie(){
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException {
        Zombie z = ZombieFactory.getZombie(zombie);

        try{
            game.addZombie(z, x, y);
        }catch (Exception e){
            throw new CommandExecuteException(e.getMessage());
        }

        return true;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParserException {
        Command c = null;

        if(commandWords[0].equals(COMMAND_TEXT)){
            if (commandWords.length != 4) throw new CommandParserException("Incorrect number of arguments for addZombie command: " + USAGE);

            c = this;
            this.zombie = commandWords[1];

            try{
                this.x = Integer.parseInt(commandWords[2]);
                this.y = Integer.parseInt(commandWords[3]);
            }catch (NumberFormatException e){
                throw new CommandParserException("Invalid argument for addZombie command, number expected: " + USAGE);
            }
        }

        return c;
    }
}
