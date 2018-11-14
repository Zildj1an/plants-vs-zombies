package tp.logic.objects;

public class WallNut extends Plant{
    private static final int COST = 50;
    private static final int HEALTH = 10;
    private static final int DAMAGE = 0;

    private static final char SYMBOL = 'N';

    public WallNut(){
        super(COST);
        this.health = HEALTH;
        super.symbol = SYMBOL;
    }

    @Override
    public String getInfo() {
        return "Wall[" + SYMBOL +"]ut: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    @Override
    public void update() {
    }
}
