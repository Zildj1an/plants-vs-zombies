package tp.logic.factories;

import tp.logic.objects.Athlete;
import tp.logic.objects.BucketHead;
import tp.logic.objects.CommonZombie;
import tp.logic.objects.Zombie;

import java.util.Random;

public class ZombieFactory {

    private static Zombie[] availableZombies = {new Athlete(), new BucketHead(), new CommonZombie()};
    private static String[] zombieNames = {"athlete", "common", "buckethead"};

    public static Zombie getZombie(String zombieName){
        Zombie z = null;

        switch (zombieName){
            case "athlete":
            case "a":
                z = new Athlete();
                break;
            case "common":
            case "c":
                z = new CommonZombie();
                break;
            case "buckethead":
            case "b":
                z = new BucketHead();
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


    /*
    public static Zombie getRandomZombie(Random rand){
        Zombie z = null;

        try {
            z = availableZombies[rand.nextInt(availableZombies.length)].getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return z;
   }
   */
}
