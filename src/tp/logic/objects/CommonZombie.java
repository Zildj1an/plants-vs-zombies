package tp.logic.objects;


public class CommonZombie extends Zombie{
    private static final int HEALTH = 5;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 1;
    private static final String NAME = "Common zombie";

    private static final char SYMBOL = 'Z';

    public CommonZombie(){
        super(FREQUENCY, DAMAGE);
        super.health = HEALTH;
        super.cycle = FREQUENCY-1;
        super.symbol = SYMBOL;
        super.name = NAME;
    }

    @Override
    public String getInfo() {
        return "[Z]ombie common: speed: " + FREQUENCY + " Harm: " + DAMAGE + " Life: " + HEALTH;
    }
}
