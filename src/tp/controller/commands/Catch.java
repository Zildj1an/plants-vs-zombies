package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;

public class Catch extends Command {

    public static final String commandText = "catch";
    public static final String commandTextMsg = "[C]atch";
    public static final String helpTextMsg = "catch sunflower in x,y position";

    private int x;
    private int y;

    public Catch(){
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        if(!game.catchSun(x, y)){
            System.out.println("there is not a sun in position x:" + x + ", y:" + y);
        }
    }

    @Override
    public Command parse(String[] commandWords, Controller controller) {
        Command c = null;

        if(commandWords.length == 3 && (commandWords[0].equals(commandText) || commandWords[0].equals(Character.toString(commandText.charAt(0))))) {
            c = this;
            x = Integer.parseInt(commandWords[1]);
            y = Integer.parseInt(commandWords[2]);
        }

        return c;
    }
}
