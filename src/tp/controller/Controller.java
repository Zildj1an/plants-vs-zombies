package tp.controller;

import tp.controller.commands.Command;
import tp.controller.commands.CommandParser;
import tp.exceptions.CommandExecuteException;
import tp.exceptions.CommandParserException;
import tp.logic.Game;
import tp.logic.printers.GamePrinter;
import tp.logic.printers.ReleasePrinter;
import tp.logic.objects.Player;

import java.util.Scanner;

public class Controller {

    private static  final String PROMPT = "COMMAND > ";
    private static final String UNKNOWNCOMMANDMSG = "Unknown command. Use 'help' to see the available commands" + System.getProperty("line.separator");
    private static  final String GAMEOVER = "Game Over";

    private Game game;
    private Scanner in;
    private GamePrinter gamePrinter;

    public Controller (Game game, Scanner in){
        this.game = game;
        this.in= in;
    }

    public void run(){
        Player winner = Player.NONE;
        printGame();
        do {
            System.out.print(PROMPT);
            String[] words = in.nextLine().toLowerCase().trim().split("\\s+");

            try{
                Command command = CommandParser.parseCommand(words);
                if (command != null){
                    if (command.execute(game))
                        printGame();
                }else
                    System.out.println(UNKNOWNCOMMANDMSG);
            }catch (CommandExecuteException | CommandParserException ex){
                System.out.format(ex.getMessage() + "%n%n");
            }

        }while(!game.hasFinished() && (winner = game.hasWinner()) == Player.NONE);
        if (game.hasFinished())
            System.out.println("****** Game over!: User exit ******");
        else
            System.out.println(GAMEOVER + "\n" + winner.getName() + " win");
    }

    private void printGame(){
        gamePrinter = game.getModePrint();
        System.out.println(gamePrinter.printGame(game));
    }
}
