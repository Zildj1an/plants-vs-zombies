package tp.logic.objects;

public class Sunflower extends Plant{
    private static final int COST = 20;
    private static final int HEALTH = 2;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 0;
    private static final int SUNCOINS = 10;

    private static final char SYMBOL = 'S';

    public Sunflower(){
        super(COST);
        super.health = HEALTH;
        super.cycle = FREQUENCY;
        super.symbol = SYMBOL;
    }

    public void update(){
        if (super.cycle == 0){
            game.incrementSuncoins(SUNCOINS);
            super.cycle = FREQUENCY;
        }else
            super.cycle--;
    }

    public String getInfo(){
        return "[" + SYMBOL +"]unflower: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }
}
