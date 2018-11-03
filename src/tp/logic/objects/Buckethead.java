package tp.logic.objects;

public class Buckethead extends Zombie{
    private static final int HEALTH = 8;
    private static final int FREQUENCY = 4;
    private static final int DAMAGE = 1;

    public Buckethead() {
        super(FREQUENCY, DAMAGE);
        super.health = HEALTH;
        super.cycle = 0;
    }


}
