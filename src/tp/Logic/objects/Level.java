package tp.Logic.objects;

public enum Level {
    EASY(3,0.1), HARD(5,0.2), INSANE(10,0.3);

    private int numZombies;
    private double frequency;

    Level(int numZombies, double frequency) {
        this.numZombies = numZombies;
        this.frequency = frequency;
    }

    public int getNumZombies() {
        return numZombies;
    }

    public double getFrequency() {
        return frequency;
    }
}
