package tp.logic.factories;

import tp.logic.objects.SportyZombie;
import tp.logic.objects.BucketheadZombie;
import tp.logic.objects.CommonZombie;
import tp.logic.objects.Zombie;

import java.util.Random;

public class ZombieFactory {

    private static Zombie[] availableZombies = {new SportyZombie(), new BucketheadZombie(), new CommonZombie()};
    private static String[] zombieNames = {"sporty", "common", "buckethead"};

    public static Zombie getZombie(String zombieName){
        Zombie z = null;

        switch (zombieName){
            case "sporty":
            case "s":
                z = new SportyZombie();
                break;
            case "common":
            case "c":
                z = new CommonZombie();
                break;
            case "buckethead":
            case "b":
                z = new BucketheadZombie();
                break;
        }

        return z;
    }

    public static String listOfAvilableZombies() {
        StringBuilder sb = new StringBuilder();
        for (Zombie z: availableZombies) {
            sb.append(z.getInfo()).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public static Zombie getRandomZombie(Random rand){
        return getZombie(zombieNames[rand.nextInt(zombieNames.length)]);
    }
}
