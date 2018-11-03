package tp.logic.objects;

public class Sunflower extends Plant{
    private static final int COST = 20;
    private static final int HEALTH = 1;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 0;
    private static final int SUNCOINS = 10;

    public Sunflower(){
        super(COST);
        super.health = HEALTH;
        super.cycle = 0;
    }

    public void update(){
        cycle++;
        if (cycle == FREQUENCY){
            game.incrementSuncoins(SUNCOINS);
            cycle = 0;
        }
    }

    public String getInfo(){
        return "[S]unflower: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    public String toString(){
        return "S["+ health +"]";
    }
}
