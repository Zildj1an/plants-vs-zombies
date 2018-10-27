package tp.controller;

import tp.logic.Game;
import tp.logic.objects.Player;

import java.util.Scanner;

public class Controller {
    private Game game;
    private Scanner in;
    private boolean isFinished = false;

    public Controller (Game game, Scanner in){
        this.game = game;
        this.in= in;
    }

    public void run(){
        Player p = Player.NONE;
        do{
            System.out.println(game.toString());
            if ((p = game.hasWinner()) != Player.NONE){
                isFinished = true;
                System.out.println("Game Over");
                System.out.println(p.getName() + " win");
            }
            if (userCommand())
                game.update();
        }while(!isFinished);

    }

    //this method return a boolean, true means that the board needed to be repainted
    private boolean userCommand(){
        boolean updateNeeded = false;
        System.out.print("COMMAND > ");
        String command[] = in.nextLine().toLowerCase().split(" ");

        switch (command[0]){
            case "add":
            case "a":
                if (command.length != 4) {
                    System.out.println("[ERROR] add argument, need type of plant, and x, y position");
                    break;
                }

                if(command[1].equals("p") || command[1].equals("peashooter")){
                    if (!game.addPeashooter(Integer.parseInt(command[2]), Integer.parseInt(command[3])))
                        System.out.println("[ERROR], [P]eashooter could not be added");
                }else if(command[1].equals("s") || command[1].equals("sunflower")){
                    if (!game.addSunflower(Integer.parseInt(command[2]), Integer.parseInt(command[3])))
                        System.out.println("[ERROR], [S]unflower could not be added");
                }else{
                    System.out.println("[ERROR],unknown plant");
                }
                updateNeeded = true;
            break;

            case "reset":
            case "r":
                game.reset();
                break;

            case "listPlantsAvailable":
            case "l":
                for(String s: game.listPlantsAvailable())
                    System.out.println(s);
                break;

            case "exit":
            case "e":
                isFinished = true;
                break;

            case "help":
            case "h":
                help();
                break;

            case "":
                updateNeeded = true;
                break;

            default:
                System.out.println("[ERROR]: Invalid command");
                break;
        }

        return updateNeeded;
    }

    private void help(){
        System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
        System.out.println("List: Prints the listPlantsAvailable of available plants.");
        System.out.println("Reset: Starts a new game.");
        System.out.println("Help: Prints this help message.");
        System.out.println("Exit: Terminates the program.");
        System.out.println("[none]: Skips cycle.");
    }
}
