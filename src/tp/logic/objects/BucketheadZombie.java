package tp.logic.objects;

public class BucketheadZombie extends Zombie{
    private static final int HEALTH = 8;
    private static final int FREQUENCY = 4;
    private static final int DAMAGE = 1;

    public BucketheadZombie() {
        super(FREQUENCY, DAMAGE);
        super.health = HEALTH;
        super.cycle = 0;
    }

    @Override
    public String getInfo() {
        return "[Z]ombie bucket head: speed: " + FREQUENCY + " Harm: " + DAMAGE + " Life: " + HEALTH;
    }
}
