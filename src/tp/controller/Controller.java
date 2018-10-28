package tp.controller;

import tp.controller.commands.Command;
import tp.controller.commands.CommandParser;
import tp.controller.commands.HelpCommand;
import tp.logic.Game;
import tp.logic.objects.Player;

import java.util.Scanner;

public class Controller {

    private static  final String PROMPT = "COMMAND > ";
    private static final String UNKNOWNCOMMANDMSG = "Invalid command";
    private static  final String GAMEOVER = "Game Over";

    private Game game;
    private Scanner in;
    private boolean noPrint;
    private boolean exit = false;

    public Controller (Game game, Scanner in){
        this.game = game;
        this.in= in;
    }

    public void run(){
        Player winner = Player.NONE;
        do {
            printGame();
            noPrint = false;

            System.out.print(PROMPT);
            String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
            Command command = CommandParser.parseCommand(words, this);

            if (command != null){
                command.execute(game, this);
                if ((winner = game.hasWinner()) != Player.NONE){
                    System.out.println(GAMEOVER + "\n" + winner.getName() + "win");
                }
            }else{
                //TODO solve err and out print order
                System.err.println(UNKNOWNCOMMANDMSG);
                setNoPrintGameState();
            }

        }while(!exit && game.hasWinner() == Player.NONE);
    }

    public void setExit(){
        this.exit = true;
    }

    public void setNoPrintGameState(){
        noPrint = true;
    }

    public void printGame(){
        if (!noPrint)
            System.out.println(game.toString());
    }
}
