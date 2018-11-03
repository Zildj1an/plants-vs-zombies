package tp.controller;

import tp.controller.commands.Command;
import tp.controller.commands.CommandParser;
import tp.logic.Game;
import tp.logic.gamePrinter.GamePrinter;
import tp.logic.gamePrinter.ReleasePrinter;
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
    private GamePrinter gamePrinter;

    public Controller (Game game, Scanner in){
        this.game = game;
        this.in= in;
        gamePrinter = new ReleasePrinter();
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
                winner = game.hasWinner();
            }else{
                System.out.println(UNKNOWNCOMMANDMSG);
                setNoPrintGameState();
            }

        }while(!exit && winner == Player.NONE);
        printGame();
        System.out.println(GAMEOVER + "\n" + winner.getName() + " win");
    }

    public void setExit(){
        this.exit = true;
    }

    public void setNoPrintGameState(){
        noPrint = true;
    }

    private void printGame(){
        if (!noPrint)
            System.out.println(game.toString());
            //System.out.println(gamePrinter.printGame(game));
    }

    public void switchPrintMode(){
        //TODO
        System.out.println("cambiando modo de pintado");
    }
}
