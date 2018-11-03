package tp.logic.objects;

public class WallNut extends Plant{
    private static final int COST = 50;
    private static final int HEALTH = 10;
    private static final int DAMAGE = 0;

    public WallNut(){
        super(COST);
        this.health = HEALTH;
    }

    @Override
    public String getInfo() {
        return "Wall[N]ut: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    @Override
    public void update() {
    }

    @Override
    public String toString() {
        return "N[" + health + "]";
    }
}
