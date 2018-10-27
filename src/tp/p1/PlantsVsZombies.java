package tp.p1;

import tp.p1.Logic.Game;
import tp.p1.Logic.objects.Level;
import tp.p1.controller.Controller;

import java.util.Scanner;

public class PlantsVsZombies {

    private static final String USAGE = "";

    public static void main(String[] args){
        Level level = null;

        if(args.length < 1) {
            System.out.println("[ERROR] Level argument is necessary");
            System.out.println(USAGE);
            System.exit(-1);
        }

        try {
            level = Level.valueOf(args[0]);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid level argument");
            System.exit(-1);
        }

        Game game = args.length == 2 ? new Game(level, Integer.parseInt(args[1])) : new Game(level);

        Scanner in = new Scanner(System.in);
        Controller controller = new Controller(game, in);
        controller.run();
    }
}