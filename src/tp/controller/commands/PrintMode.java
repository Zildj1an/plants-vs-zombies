package tp.controller.commands;

import tp.controller.Controller;
import tp.logic.Game;
import tp.logic.gamePrinter.DebugPrinter;
import tp.logic.gamePrinter.ReleasePrinter;

public class PrintMode extends Command {

    public static final String commandText = "printmode";
    public static final String commandTextMsg = "[P]rintMode";
    public static final String helpTextMsg = "change print mode [Release|Debug].";

    private String mode;

    public PrintMode(){
        super(commandText, commandTextMsg, helpTextMsg);
    }

    @Override
    public void execute(Game game, Controller controller) {
        switch (mode){
            case "debug":
                controller.setModePrint(new DebugPrinter());
                break;
            case "release":
                controller.setModePrint(new ReleasePrinter());
                break;
            default:
                System.out.println("[ERROR] invalid option");
        }
    }

    @Override
    public Command parse(String[] commandWords, Controller controller) {
        Command c = null;

        if(commandWords.length == 2 && (commandWords[0].equals(commandText) || commandWords[0].equals(Character.toString(commandText.charAt(0))))) {
            c = this;
            this.mode = commandWords[1];
        }
        return c;
    }
}
