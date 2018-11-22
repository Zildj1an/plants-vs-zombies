package tp.logic.objects;

public class SportyZombie extends Zombie {
    private static final int HEALTH = 5;
    private static final int FREQUENCY = 1;
    private static final int DAMAGE = 1;

    public SportyZombie(){
        super(FREQUENCY, DAMAGE);
        super.health = HEALTH;
        super.cycle = FREQUENCY-1;
    }


    @Override
    public String getInfo() {
        return "[Z]ombie athlete: speed: " + FREQUENCY + " Harm: " + DAMAGE + " Life: " + HEALTH;
    }
}
