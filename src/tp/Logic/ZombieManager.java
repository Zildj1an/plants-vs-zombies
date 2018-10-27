package tp.Logic;

import tp.Logic.objects.Level;

import java.util.Random;

public class ZombieManager {
    private int remainingZombies;
    private double frequency;
    private Random rand;

    public ZombieManager(Level level, Random rand) {
        this.remainingZombies = level.getNumZombies();
        this.frequency = level.getFrequency();
        this.rand = rand;

    }

    public int getRemainingZombies() {
        return remainingZombies;
    }

    public void updateRemainingZombies() {
        this.remainingZombies--;
    }

    public boolean isZombieAdded(){
        return rand.nextDouble() < frequency && remainingZombies != 0;
    }
}
