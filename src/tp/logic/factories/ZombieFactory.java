package tp.logic.factories;

import tp.logic.objects.Athlete;
import tp.logic.objects.Buckethead;
import tp.logic.objects.CommonZombie;
import tp.logic.objects.Zombie;

public class ZombieFactory {

    private static Zombie[] availableZombies = {new Athlete(), new Buckethead(), new CommonZombie()};

    public static Zombie getZombie(String zombieName){
        Zombie z = null;

        switch (zombieName){
            case "athlete":
            case "a":
                //TODO change constructor
                z = new Athlete();
                break;
            case "common":
            case "c":
                z = new CommonZombie();
                break;
            case "buckethead":
            case "b":
                z = new Buckethead();
                break;
        }

        return z;
    }

    public static String listOfAvilableZombies() {
        StringBuilder sb = new StringBuilder();
        for (Zombie z: availableZombies) {
            //TODO implement this
            //sb.append(z.toString());
        }
        return sb.toString();
    }
}
